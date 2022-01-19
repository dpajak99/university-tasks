# MIT License
#
# Copyright (c) 2017 François Chollet
#
# Permission is hereby granted, free of charge, to any person obtaining a
# copy of this software and associated documentation files (the "Software"),
# to deal in the Software without restriction, including without limitation
# the rights to use, copy, modify, merge, publish, distribute, sublicense,
# and/or sell copies of the Software, and to permit persons to whom the
# Software is furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
# THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
# FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
# DEALINGS IN THE SOFTWARE.

# Przykład programu Keras/TensorFlow od tensorflow.org
# https://www.tensorflow.org/tutorials/keras/classification
# Opracowanie: Dominik Pająk, Jakub Serwin, Bartosz Dymański

# TensorFlow and tf.keras
import tensorflow as tf

# Helper libraries
import numpy as np
import matplotlib.pyplot as plt

# Sprawdzenie wersji TensorFlow
print(tf.__version__)

# Ten przewodnik szkoli model sieci neuronowej do klasyfikowania obrazów odzieży takich jak trampki i koszule. 

# Wykorzystujemy tf.keras - API wysokiego poziomu do budowania i uczenia modeli TensorFlow.
fashion_mnist = tf.keras.datasets.fashion_mnist

# Importujemy przykładowe dane
# train_images i train_labels to zbiór obrazów i ich etykiet wykorzystywanych do uczenia naszego modelu
# test_images oraz test_labels będą naszym zbiorem do testowania
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()

# Obrazy są o rozmiarze 28x28 pikseli w kolorach z zakresu od 0 do 255 
# Etykiety są tablicą liczb całkowitych z zakresu od 0 do 9, dlatego przypisujemy tym liczbom całkowitym odpowiednią etykietę w formie tekstowej
#
#  - 0 - T-shirt/Top
#  - 1 - Spodnie
#  - 2 - Bluzka
#  - 3 - Sukienka
#  - 4 - Płaszcz
#  - 5 - Sandał
#  - 6 - Koszula
#  - 7 - Trampki
#  - 8 - Torba
#  - 9 - Buty za kostkę 

class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat', 'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']

# Przyjrzyjmy się formatowi zestawu danych przed trenowaniem modelu. 
# Poniżej pokazano, że w zestawie do uczenia znajduje się 60 000 obrazów, a każdy obraz jest reprezentowany jako 28 x 28 pikseli:
#
# > (60000, 28, 28)
print("\n\n > Informacje o zestawie treningowym")
print("train_images.shape = ", train_images.shape)

# Podobnie w zestawie do uczenia znajduje się 60 000 etykiet:
# 
# 60000
print("len(train_labels) = ", len(train_labels))

# Każda etykieta jest liczbą całkowitą od 0 do 9:
# 
# array([9, 0, 0, ..., 3, 0, 5], dtype=uint8)
print("train_labels = ", train_labels)

# Podobne sprawdzenie możemy zrobić dla zestawou do testowania
print("\n\n > Informacje o zestawie testowym")
# (10000, 28, 28)
print("test_images.shape = ", test_images.shape)

# 10000
print("len(test_labels) = ", len(test_labels))

# Dane muszą zostać wstępnie przetworzone przed uczeniem sieci. 
# Jeśli przyjrzysz się pierwszemu obrazowi w zestawie do uczenia, 
# zobaczysz, że wartości pikseli mieszczą się w zakresie od 0 do 255:
print("\n\n > Przykładowy obraz przed przeformatowaniem")
plt.figure()
plt.imshow(train_images[0])
plt.colorbar()
plt.grid(False)
plt.show()

# Przeskaluj te wartości do zakresu od 0 do 1 przed wprowadzeniem ich do modelu sieci neuronowej. 
# Aby to zrobić, należy podzielić wartości przez 255. 
# Ważnym jest, że zbiór do uczenia i zbiór do testów musi być wstępnie przetworzony w taki sam sposób:
train_images = train_images / 255.0

test_images = test_images / 255.0

# Aby sprawdzić, czy dane są w odpowiednim formacie oraz czy są gotowe do kompilacji aby trenować sieć, 
# należy wyświetlić pierwsze 25 zdjęć ze zbioru uczenia i wyświetlić nazwę klasy pod każdym obrazem.
print("\n\n > Przykładowe obrazy po przeformatowaniu")
plt.figure(figsize=(10,10))
for i in range(25):
    plt.subplot(5,5,i+1)
    plt.xticks([])
    plt.yticks([])
    plt.grid(False)
    plt.imshow(train_images[i], cmap=plt.cm.binary)
    plt.xlabel(class_names[train_labels[i]])
plt.show()

# Podstawowym budulcem sieci neuronowej jest warstwa.
# Większość uczenia głębokiego polega na łączeniu prostych warstw.
model = tf.keras.Sequential([
                             
    # Pierwsza warstwa w tej sieci, tf.keras.layers.Flatten 
    # przekształca obraz z dwuwymiarowej tablicy (od 28 do 28 pikseli) do jednowymiarowej tablicy (28 * 28 = 784 pikseli). 
    # Pomyśl o tej warstwie jako o rozłożeniu rzędów pikseli na obrazie i wyrównaniu ich. 
    # Ta warstwa nie ma parametrów do nauki - tylko formatuje dane.
    #
    # Przykładowo obraz
    # 03303
    # 00303
    # 00000
    # 00330
    #
    # Zostaje przekształcony na
    # 0330300303000000330
    tf.keras.layers.Flatten(input_shape=(28, 28)),

    # Po spłaszczeniu pikseli, sieć składa się z dwóch sekwencji tf.keras.layers.Dense. 
    # Są to gęsto połączone lub w pełni połączone warstwy neuronowe. 
    # Pierwsza warstwa Dense ma 128 węzłów (lub neuronów). 
    # Druga (i ostatnia) warstwa Dense zwraca tablicę o długości 10. 
    # Każdy węzeł zawiera wynik wskazujący, że bieżący obraz należy do jednej z 10 klas.
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(10)
])

# Zanim model będzie gotowy do trenowania, potrzebuje kilku dodatkowych ustawień. 
# Są one dodawane podczas kompilacji modelu:
#
# Loss function - mierzy dokładność modelu podczas uczenia
# Optimizer - odpowiada za to jak model jest aktualizowany, bazując na danych i funkcji straty
# Metrics - używane do monitorowania etapów uczenia i testowania
model.compile(optimizer='adam',
              loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
              metrics=['accuracy'])

# Trenowanie sieci neuronowej wymaga następujacych etapów:
# 
# - Dostarczenie danych do modelu. W tym przypadku dane dreningoweto train_images oraz train_labels
# - Model uczy się jak przypisywać obrazy i etykiety
# - Zapytanie do modelu o rozpoznanie danych testowych
# - Zweryfikowanie czy wynik jest poprawny
#
# Aby zacząć trenować nasz model wywołaj metodę model.fit method.
# Jest ona tak nazywana ponieważ (z ang.): "it "fits" the model to the training data""
#
# W miarę trenowania modelu wyświetlane są metryki strat i dokładności. 
# Model ten osiąga dokładność około 0,91 (lub 91%) na danych treningowych.
print("\n\n > Trenowanie modelu")
model.fit(train_images, train_labels, epochs=10)

# Następnie porównaj, jak model działa na testowym zbiorze danych:
test_loss, test_acc = model.evaluate(test_images,  test_labels, verbose=2)
print('\n\nTest accuracy:', test_acc)

# Okazuje się, że dokładność zestawu danych testowych jest nieco mniejsza niż dokładność zestawu danych treningowych. 
# Ta luka między dokładnością treningu a dokładnością testu to (Overfitting (przesadne dopasowanie)). 
# Overfitting ma miejsce, gdy model uczenia maszynowego działa gorzej na nowych, wcześniej niewidocznych danych wejściowych niż na danych szkoleniowych. 
# Przesadnie dopasowany model „zapamiętuje” szum i szczegóły w uczącym zestawie danych do punktu, w którym negatywnie wpływa na wydajność modelu na nowych danych. 
#
# Po przeszkoleniu modelu można go używać do przewidywania niektórych obrazów. 
probability_model = tf.keras.Sequential([model, tf.keras.layers.Softmax()])
predictions = probability_model.predict(test_images)
print("\npredictions[0] = ", predictions[0])

# Predykcja jest tablicą 10 liczb reprezentujących 10 różnych artykułów odzieżowych. 
# Możemy również zobaczyć, która etykieta ma najwyższy wskaźnik:
print("\nnp.argmax(predictions[0]) = ", np.argmax(predictions[0]))

# Tak więc model jest najbardziej przekonany, że wybrany obraz to buty za kostkę, lub class_names[9]. 
# Badanie etykiety testowej pokazuje, że ta klasyfikacja jest prawidłowa:
print( "\ntest_labels[0] = ", test_labels[0] )

# Następnie odrobina magicznego kodu, którego nie będę tłumaczył.
# Tworzy on dwie funkcje do ładnego zwizualizowania naszych wyników

# Rysuje wybrany obraz na wykresie
def plot_image(i, predictions_array, true_label, img):
  true_label, img = true_label[i], img[i]
  plt.grid(False)
  plt.xticks([])
  plt.yticks([])

  plt.imshow(img, cmap=plt.cm.binary)

  predicted_label = np.argmax(predictions_array)
  if predicted_label == true_label:
    color = 'blue'
  else:
    color = 'red'

  plt.xlabel("{} {:2.0f}% ({})".format(class_names[predicted_label],
                                100*np.max(predictions_array),
                                class_names[true_label]),
                                color=color)

# Rysuje wykres "przekonania" modelu do poszczególnych etykiet
def plot_value_array(i, predictions_array, true_label):
  true_label = true_label[i]
  plt.grid(False)
  plt.xticks(range(10))
  plt.yticks([])
  thisplot = plt.bar(range(10), predictions_array, color="#777777")
  plt.ylim([0, 1])
  predicted_label = np.argmax(predictions_array)

  thisplot[predicted_label].set_color('red')
  thisplot[true_label].set_color('blue')

# Następnie możemy sprawdzić działanie naszego modelu
NUMBER_OF_IMAGES_TO_TEST = 5
for i in range(NUMBER_OF_IMAGES_TO_TEST):
  plt.figure(figsize=(6,3))
  plt.subplot(1,2,1)
  plot_image(i, predictions[i], test_labels, test_images)
  plt.subplot(1,2,2)
  plot_value_array(i, predictions[i],  test_labels)
  plt.show() 
