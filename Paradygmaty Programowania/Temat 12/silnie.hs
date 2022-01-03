-- SKOŃCZONE
-- Dominik Pająk - 13.05.2021 - 15:20

silnia1 x
 | x < 0 = -1
 | x == 0 = 1
 | otherwise = x * silnia1(x-1)

silnia2 x = case x > 0 of
 True -> x * silnia2(x-1)
 False -> if x < 0 then -1
                    else 1
