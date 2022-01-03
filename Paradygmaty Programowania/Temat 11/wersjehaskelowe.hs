-- SKOŃCZONE
-- Dominik Pająk 13.05.2021 - 14:23

srednia1 x = sum x `div` length x
srednia2 x = foldr (+) 0 x `div` length x
splaszcz x = foldl (++) [] x
iloczyn1 x = map(\ i -> (i*length x)) x
iloczyn2 x = map(*length x) x