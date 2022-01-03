-- SKOŃCZONE
-- Dominik Pająk - 13.05.2021 - 13:37
dodaj_i_podziel = map (\ x -> (x+1)/2)
dodaj_i_podziel2 = map ((/2) . (+1))
dodaj_i_podziel3 = map (/2 ) . map ((+1))


