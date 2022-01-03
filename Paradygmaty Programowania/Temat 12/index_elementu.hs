-- SKOŃCZONE
-- Dominik Pająk - 25.05.2021 - 15:16
-- Nie wiedziałem jak wrzucić blok where do pierwszej funkcji

rfindIndex1 list item index size = if size > -1 
 then do
  if list!!size == item then do
   let index = size
   return index
  else do
   result <- rfindIndex1 list item index (size - 1)
   return result
 else
  return (-1)

  
rfindIndex2 list item index size 
 | i == size = return (-1)
 | list!!i == item = do
   let index = i
   return index
 | otherwise = do
   result <- rfindIndex2 list item (index + 1) size
   return result
 where i = index

findIndex1 list item = rfindIndex1 list item (-1) ((length list)-1)
findIndex2 list item = rfindIndex2 list item 0 (length list)
 