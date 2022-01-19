cars

model <- lm( dist ~ 1 +speed, data=cars)
w_determincaji = summary(model)$r.square
print("Współczynnik determincacji")
w_determincaji
plot(cars$speed, cars$dist) 
abline(model)
grid()
cor(cars$speed, cars$dist, method="spearman") 

