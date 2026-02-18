Proceso TrianguloMejorado
    
    // cargar datos
    Escribir "Ingrese el lado 1:"
    Leer l1
    Escribir "Ingrese el lado 2:"
    Leer l2
    Escribir "Ingrese el lado 3:"
    Leer l3
    
    // verificar que formen un triángulo (desigualdad triangular)
    Si l1+l2>l3 Y l1+l3>l2 Y l2+l3>l1 Entonces
        
        // encontrar la hipotenusa (mayor lado)
        Si l1>l2 Entonces
            cat1<-l2
            Si l1>l3 Entonces
                hip<-l1
                cat2<-l3
            Sino
                hip<-l3
                cat2<-l1
            FinSi
        Sino
            cat1<-l1
            Si l2>l3 Entonces
                hip<-l2
                cat2<-l3
            Sino
                hip<-l3
                cat2<-l2
            FinSi
        FinSi
        
        // verificar si es rectángulo
        Si hip^2 = cat1^2 + cat2^2 Entonces
            
            area<-(cat1*cat2)/2
            Escribir "Es un triangulo rectangulo."
            Escribir "El area es: ", area
            
        Sino
            
            // clasificar según sus lados
            Si l1=l2 Y l2=l3 Entonces
                Escribir "Es un triangulo equilatero."
            Sino
                Si l1=l2 O l1=l3 O l2=l3 Entonces
                    Escribir "Es un triangulo isosceles."
                Sino
                    Escribir "Es un triangulo escaleno."
                FinSi
            FinSi
            
        FinSi
        
    Sino
        
        Escribir "Los lados ingresados NO forman un triangulo."
        
    FinSi
	
FinProceso
