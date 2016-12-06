#!/bin/bash
echo "" > output.txt

for i in {1..10};
do
	java -jar PracticaBusquedaTabu.jar | tail -n 4 >> output.txt
done

grep -r "COSTE" -i output.txt > resDis.txt 
sed 's/.*COSTE (km): \([0-9\.]*\)/\1/g' resDis.txt > resultados.txt
cp resultados.txt resultadosDis.txt
rm resDis.txt
python estadisticos.py > EstadisticosDistancias.txt
grep -r "ITERACION" -i output.txt > resIt.txt 
sed 's/.*ITERACION: \([0-9\.]*\)/\1/g' resIt.txt > resultados.txt
cp resultados.txt resultadosIt.txt
rm resIt.txt
python estadisticos.py > EstadisticosMejorIteracion.txt
rm output.txt
rm resultados.txt