Proceso Romanos_Suma
    
    // Leer los dos números
    Escribir "Ingrese el primer número (1 a 1000):"
    Leer Numero1
    
    Escribir "Ingrese el segundo número (1 a 1000):"
    Leer Numero2
    
    SePuedeConvertir<-Verdadero
    
    // Validación básica
    Si Numero1<=0 O Numero2<=0 Entonces
        Escribir "Ambos números deben ser positivos."
        SePuedeConvertir<-Falso
    FinSi
    
    Si Numero1<>trunc(Numero1) O Numero2<>trunc(Numero2) Entonces
        Escribir "Ambos números deben ser enteros."
        SePuedeConvertir<-Falso
    FinSi
    
    // Sumar
    Si SePuedeConvertir Entonces
        
        Suma<-Numero1+Numero2
        
        Escribir "La suma es: ", Suma
        
        // Verificar límite romano
        Si Suma>1000 Entonces
            Escribir "El resultado supera 1000 y no puede convertirse."
        Sino
            
            Si Suma=1000 Entonces
                Escribir "En números romanos es: M"
            Sino
                
                Dimension nu[10], nd[10], nc[10]
                
                nu[1]<-''; nu[2]<-'I'; nu[3]<-'II'; nu[4]<-'III'
                nu[5]<-'IV'; nu[6]<-'V'; nu[7]<-'VI'
                nu[8]<-'VII'; nu[9]<-'VIII'; nu[10]<-'IX'
                
                nd[1]<-''; nd[2]<-'X'; nd[3]<-'XX'; nd[4]<-'XXX'
                nd[5]<-'XL'; nd[6]<-'L'; nd[7]<-'LX'
                nd[8]<-'LXX'; nd[9]<-'LXXX'; nd[10]<-'XC'
                
                nc[1]<-''; nc[2]<-'C'; nc[3]<-'CC'; nc[4]<-'CCC'
                nc[5]<-'CD'; nc[6]<-'D'; nc[7]<-'DC'
                nc[8]<-'DCC'; nc[9]<-'DCCC'; nc[10]<-'CM'
                
                centenas<-trunc(Suma/100) MOD 10
                decenas<-trunc(Suma/10) MOD 10
                unidades<-Suma MOD 10
                
                Escribir "En números romanos es: ", nc[centenas+1], nd[decenas+1], nu[unidades+1]
                
            FinSi
            
        FinSi
        
    FinSi
	
FinProceso
