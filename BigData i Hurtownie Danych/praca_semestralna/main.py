import sys
import pandas as pd
import plotly.express as px
import matplotlib
import matplotlib.pyplot as plt
import seaborn as sns
import pandas_profiling

matplotlib.use('TkAgg')

# Config warning settings
plt.rcParams.update({'figure.max_open_warning': 0})

# Wprowadzenie danych
covid_data = pd.read_csv("./inputs/covid-variants.csv")


def analysis():
    if len(covid_data.select_dtypes("object").columns) > 0:
        print("Object Variables:", "\n",
              len(covid_data.select_dtypes("object").columns), "\n",
              covid_data.select_dtypes("object").columns.tolist(), "\n")

    if len(covid_data.select_dtypes("integer").columns) > 0:
        print("Integer Variables:", "\n",
              len(covid_data.select_dtypes("integer").columns), "\n",
              covid_data.select_dtypes("integer").columns.tolist(), "\n")

    if len(covid_data.select_dtypes("float").columns) > 0:
        print("Float Variables:", "\n",
              len(covid_data.select_dtypes("float").columns), "\n",
              covid_data.select_dtypes("float").columns.tolist(), "\n")

    if len(covid_data.select_dtypes("bool").columns) > 0:
        print("Bool Variables:", "\n",
              len(covid_data.select_dtypes("bool").columns), "\n",
              covid_data.select_dtypes("bool").columns.tolist(), "\n")


# Sprawdzenie, czy występują braki
# Sprawdzamy, czy zbiór danych nie ma zduplikowanych wierszy
def profileDataset():
    profiling = pandas_profiling.ProfileReport(covid_data)
    profiling.to_file("outputs/profiling.html")
    print(covid_data.head())


# Lista najbardziej zainfekowanych krajów (przez Covid-19)
def generateCountryInfectionTreemap():
    sample = covid_data.rename(
        columns={"location": "Kraj", "num_sequences_total": "Liczba przypadków"})
    fig = px.treemap(sample, path=[px.Constant('Liczba przypadków'), 'Kraj'],
                     values='Liczba przypadków', hover_data=['Kraj'], )
    fig.show()
    # fig.write_image('./outputs/infection_treemap.png', format='.png')


def generateMoreThanOthersDiagram():
    for virus in covid_data.variant.unique():
        dataframe = covid_data.loc[covid_data['variant'] == virus].groupby('location')[
                        'num_sequences'].agg('sum').sort_values(ascending=False)[:10]
        dataframe = pd.DataFrame(
            {'Kraj': dataframe.index, 'Liczba przypadków': dataframe.values})
        plt.figure(figsize=(8, 2), dpi=200)
        sns.barplot(y='Kraj', x="Liczba przypadków",
                    data=dataframe, palette="Blues_d")
        plt.title(
            'KRAJE, KTÓRE MAJĄ WIĘCEJ PRZYPADKÓW {} OD INNYCH WARIANTÓW'.format(virus),
            loc='center',
                  fontweight="bold")
        plt.savefig('./outputs/variants/{}.png'.format(virus))


def generateOmicronVariantWorld():
    sample = covid_data.loc[covid_data['variant'] == 'Omicron'].groupby('date')[
        'num_sequences'].agg('sum')
    dataframe = pd.DataFrame({'Data': sample.index, 'Liczba przypadków': sample.values})
    fig = px.area(dataframe, y="Liczba przypadków", x='Data')
    fig.show()
    # fig.write_image('./outputs/omicron_world.png', format='.png')


def generateOmicronVariantPoland():
    sample = covid_data.loc[covid_data['variant'] == 'Omicron'].loc[
        covid_data['location'] == 'Poland'].groupby('date')['num_sequences'].agg('sum')
    dataframe = pd.DataFrame({'Data': sample.index, 'Liczba przypadków': sample.values})
    fig = px.area(dataframe, y="Liczba przypadków", x='Data')
    fig.show()
    # fig.write_image('./outputs/omicron_poland.png', format='.png')


def progressionOfCovidVariantsDiagramWorld():
    variant_totals = covid_data.groupby([
        "variant", "date"], as_index=False).sum()
    progressionOfCovidVariantsDiagram(
        variant_totals=variant_totals, plottitle='Statystyki globalne')


def progressionOfCovidVariantsDiagramPoland():
    variant_totals = covid_data.loc[
        covid_data['location'] == 'Poland'].groupby([
        "variant", "date"],as_index=False).sum()
    progressionOfCovidVariantsDiagram(
        variant_totals=variant_totals, plottitle='Statystyki dla Polski')


def progressionOfCovidVariantsDiagram(variant_totals, plottitle):
    variant_totals["perc_sequences"] = (
            100 * variant_totals["num_sequences"] / variant_totals["num_sequences_total"]
    )

    fig = px.line(
        variant_totals,
        x="date",
        y="num_sequences",
        color="variant",
        color_discrete_sequence=px.colors.qualitative.Light24,
        custom_data=["perc_sequences"],
        markers=True,
        height=600,
        width=960,
        title="Postęp zakażeń wariantami wirusa COVID <br><sup>({})<sup>".format(plottitle),
    )
    fig.update_layout(
        font_family="serif", plot_bgcolor="#fff", title_font_size=20, title_x=0.5
    )
    fig.update_traces(
        hovertemplate="<i>%{x}:</i> <b>%{y}</b> (%{customdata[0]:.2f}%)",
        marker=dict(symbol="square", size=5)
    )
    fig.update_yaxes(
        fixedrange=True, gridcolor="#ddd", tickformat=",", title="Liczba przypadków"
    )
    fig.update_xaxes(fixedrange=True, title="Data")
    fig.show()


def main():
    print("Python version")
    print(sys.version)
    # analysis()

    # # Waliduje zestawienie danych i zapisuje wynik do pliku /outputs/profiling.html
    # #######################
    # profileDataset()
    #
    # # Generowanie listy najbardziej zainfekowanych krajów (przez Covid-19)
    # # Na podstawie poniższego wykresu możemy łatwo zauważyć, że Stany Zjednoczone wraz z Wielką Brytanią
    # # stanowią czołówkę, jeżeli chodzi o liczbę przypadków zarażenia koronawirusem
    # #######################
    # generateCountryInfectionTreemap()
    #
    # generateMoreThanOthersDiagram()
    # generateOmicronVariantWorld()
    # generateOmicronVariantPoland()
    # progressionOfCovidVariantsDiagramWorld()
    # progressionOfCovidVariantsDiagramPoland()


main()
