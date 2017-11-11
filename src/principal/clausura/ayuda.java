package principal.clausura;

/*Grafos: Algoritmos de Floyd y Warshall

Muchos problemas de la vida cotidiana se pueden expresar e incluso resolver en forma de grafo.
Existen algoritmos que encuentran distintos tipos de soluciones, tanto booleanas como de eficiencia.
El grafo se representa en una tabla (matriz, arreglo ; para los lenguajes de programacion).
que se conoce como “matriz de adyacencia” y representa si existe una union entre dos nodos (boolean).
o el “peso” entre dos nodos (grafo ponderado).

El algoritmo de Warshall es un ejemplo de algoritmo booleano. A partir de una tabla inicial compuesta
de 0`s (no hay correspondencia inicial en el grafo) y 1`s (hay una correspondencia, llamase “flecha”,
entre nodos), obtiene una nueva matriz denominada “Matriz de Clausura Transitiva” en la que se muestran todas las posibles uniones entre nodos, directa o indirectamente. Es decir, si de “A” a “B” no hay una “flecha”, es posible que si haya de “A” a “C” y luego de “C” a “B”. Luego, este resultado se vera volcado en la matriz final.

El algoritmo de Floyd es muy similar, pero trabaja con grafos ponderados. Es decir, el valor de la
“flecha” que representamos en la matriz puede ser cualquier entero que se nos ocurra, o infinito.
Infinito marca que no existe union entre los nodos. Esta vez, el resultado sera una matriz donde
estaran representadas las distancias minimas entre nodos, seleccionando los caminos mas convenientes
segun su ponderacion (“peso”). Por ejemplo, si de “A” a “B” hay 36 (km), pero de “A” a “C”
hay 2(km) y de “C” a “B” hay 10 (km), el algoritmo nos devolvera finalmente que de “A” a “C” hay 12 (km).

Las implementaciones en Java son las siguientes:*/