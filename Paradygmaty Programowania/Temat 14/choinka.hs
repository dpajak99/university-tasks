-- SKOŃCZONE
-- Dominik Pająk - 25.05.2021 14:07

drawBranch h i line symbol = if i == h 
 then return line
 else do
  let newLine = line ++ symbol ++ symbol
  result <- drawBranch h (i-1) newLine symbol
  return result

drawSpaces h i line = if i == h
 then return line  
 else do
  let newLine = line ++ " "
  result <- drawSpaces h (i+1) newLine
  return result


drawTree h i symbol = do
 if h == 0
 then putStrLn("")
 else do
  line <- drawBranch h i symbol symbol
  spaces <- drawSpaces (h-1) 0 "" 
  putStrLn(spaces ++ line)
  drawTree (h-1) i symbol

initTree h symbol = drawTree h h symbol

main :: IO ()
main = do
 print("Podaj rozmiar drzewa")
 inputTreeSize <- getLine
 let treeSize = (read inputTreeSize :: Int)
 if mod treeSize 2  == 0
 then initTree treeSize "*"
 else initTree treeSize "#"
 if treeSize /= 7
 then main
 else print("break")