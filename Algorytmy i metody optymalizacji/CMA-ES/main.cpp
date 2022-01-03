#include "newmat/newmat.h"
#include "newmat/newmatio.h"
#include "newmat/newmatap.h"
#include "newmat/controlw.h"
#include "newmat/include.h"
#include "newmat/myexcept.h"
#include "newmat/newmatnl.h"
#include "newmat/newmatrc.h"
#include "newmat/newmatrm.h"
#include "newmat/precisio.h"
#include "newmat/solution.h"

#include <iostream>
#include <random>

std::default_random_engine gen;

double fitness(const RowVector& row) {
    return -(100 * sqrt(abs(row.element(1) - 0.01*pow(row.element(0), 2))) + 0.01 * abs(row.element(0) + 10));
}

void sort(Matrix& m) {
    bool terminated;
    do {
        terminated = true;
        for (int i = 1; i < m.nrows(); i++) {
            if (fitness(m.row(i + 1)) > fitness(m.row(i))) {
                RowVector r;
                r << m.row(i);
                m.row(i) << m.row(i + 1);
                m.row(i + 1) << r;
                terminated = false;
            }
        }
    } while (!terminated);
}

SymmetricMatrix empCov(const Matrix& m) {
    SymmetricMatrix cov(m.ncols());
    cov = 0.0;
    ColumnVector colSums = m.sum_columns().t();

    for (int i = 0; i < m.nrows(); i++) {
        Matrix bracket = m.row(i + 1).t() - 1.0 / m.nrows()*colSums;
        SymmetricMatrix aux;
        aux << bracket * bracket.t();
        cov += aux;
    }

    return 1.0 / (m.nrows() - 1)* cov;
}

double ENDI(int n) {
    return sqrt(n) * (1 - 1.0 / (4 * n) + 1.0 / (21 * n*n));
}

int main()
{
    const int nrows = 20, ncols = 2;
    Matrix population(nrows, ncols);

    Matrix means(ncols, 1);
    means = 0.0;
    SymmetricMatrix cov(ncols);
    ColumnVector pc(ncols), psigma(ncols);
    pc = 0.0, psigma = 0.0;

    int mu = nrows / ncols;
    double wi = 1.0 / mu;
    double mueff = std::pow(mu*wi, 2) / (mu * pow(wi, 2));
    double mucov = mueff;
    double ccov = (1 / mucov) * 2 /std::pow(ncols + 1.4, 2)
    + (1 - 1 / mucov) * ((2 * mueff - 1) / (std::pow(ncols + 2, 2) + 2 * mueff));
    double cc = 1.0 / ncols;
    double csigma = cc;
    double dsigma = 1;
    double sigma = 1.0;

    DiagonalMatrix D;
    Matrix V;

    for (int i = 0; i < nrows; i++) {
        for (int j = 0; j < ncols; j++) {
            population.element(i, j) = std::uniform_int_distribution<int>(-5, 5)(gen);
        }
    }
    means = (population.sum_columns() / double(nrows)).t();

    std::cout << population << "\n\n";
    std::cout << means.t();

    cov = empCov(population);
    std::cout << cov;

    sort(population);
    std::cout << "\n\nSorted population:\n" << population;

    for (int k = 0; k < 100 && sigma < 0.00000001; k++) {
        std::cout << "iteration " << k << " - sigma" << sigma << ":\n" << population << "\n\n\nFitness:\n";
        for (int i = 0; i < nrows; i++) {
            std::cout << fitness(population.row(i+1)) << "\n";
        }


    eigenvalues(cov, D, V);
    for (int l = 0; l < D.nrows(); l++) {
        D.element(1, 1) = sqrt(D.element(1, 1));
    }

    for (int i = 0; i < nrows; i++) {
        ColumnVector randVec(ncols);
        for (int j = 0; j < ncols; j++) {
            randVec.element(j) = std::normal_distribution<double>(0, 1)(gen);
        }
        Matrix aux = means + sigma * V*D*randVec;
        population.row(i + 1) = aux.t();
    }
    sort(population);

    Matrix mnew(ncols, 1);
    mnew = 0.0;
    for (int i = 0; i < mu; i++) {
        mnew += (wi*population.row(i + 1)).t();
    }
    pc = (1 - cc) * pc + sqrt(cc * (2 - cc)* mueff)*(mnew - means) / sigma;

    Matrix muupdate(ncols, ncols);
    muupdate = 0.0;
    for (int i = 0; i < mu; i++) {
        Matrix rhs = (population.row(i + 1).t() - means) / sigma;
        muupdate += wi * rhs*rhs.t();
    }
    cov << (1 - ccov)*cov + ccov / mucov * pc*pc.t() + ccov * (1 - 1 / mucov)*muupdate;

    SymmetricMatrix cov12;
    cov12 << V * D.i()*V.t();
    psigma = (1 - csigma)*psigma + sqrt(csigma*(2 - csigma)*mueff)*cov12*(mnew - means) / sigma;

    sigma = sigma*exp(csigma / dsigma * (sqrt(psigma.sum_square()) / ENDI(ncols) - 1));
    means << mnew;
    }
    std::cin.get();

}
