Proceso TaTeTi_Mejorado
    
    // Inicialización de matrices
    Dimension Tab1[3,3]
    Dimension Tab2[3,3]
    
    Para i<-1 Hasta 3 Hacer
        Para j<-1 Hasta 3 Hacer
            Tab1[i,j]<-0
            Tab2[i,j]<-" "
        FinPara
    FinPara
    
    // ===== NUEVO: Elección de ficha =====
    Repetir
        Escribir "Jugador 1, elija su ficha (X/O): "
        Leer FichaJ1
        FichaJ1 <- Mayusculas(FichaJ1)
    Hasta Que FichaJ1="X" O FichaJ1="O"
    
    Si FichaJ1="X" Entonces
        FichaJ2<-"O"
    Sino
        FichaJ2<-"X"
    FinSi
    
    TurnoJugador1<-Verdadero
    Terminado<-Falso
    Ganador<-Falso
    CantTurnos<-0
    
    Mientras ~ Terminado Hacer
        
        Borrar Pantalla
        Escribir " "
        Escribir "      ||     ||     "
        Escribir "   ",Tab2[1,1],"  ||  ",Tab2[1,2],"  ||  ",Tab2[1,3]
        Escribir "     1||    2||    3"
        Escribir " =====++=====++======"
        Escribir "      ||     ||     "
        Escribir "   ",Tab2[2,1],"  ||  ",Tab2[2,2],"  ||  ",Tab2[2,3]
        Escribir "     4||    5||    6"
        Escribir " =====++=====++======"
        Escribir "      ||     ||     "
        Escribir "   ",Tab2[3,1],"  ||  ",Tab2[3,2],"  ||  ",Tab2[3,3]
        Escribir "     7||    8||    9"
        Escribir " "
        
        Si ~Ganador Y CantTurnos<9 Entonces
            
            // Determinar ficha y valores según turno
            Si TurnoJugador1 Entonces
                Ficha<-FichaJ1
                Valor<-1
                Objetivo<-1
                Escribir "Turno del jugador 1 (",Ficha,")"
            Sino
                Ficha<-FichaJ2
                Valor<-2
                Objetivo<-8
                Escribir "Turno del jugador 2 (",Ficha,")"
            FinSi
            
            Escribir "Ingrese la Posición (1-9):"
            
            Repetir
                Leer Pos
                Si Pos<1 O Pos>9 Entonces
                    Escribir "Posición incorrecta, ingrese nuevamente:"
                    Pos<-99
                Sino
                    i<-trunc((Pos-1)/3)+1
                    j<-((Pos-1) MOD 3)+1
                    Si Tab1[i,j]<>0 Entonces
                        Pos<-99
                        Escribir "Posición ocupada, ingrese nuevamente:"
                    FinSi
                FinSi
            Hasta Que Pos<>99
            
            CantTurnos<-CantTurnos+1
            Tab1[i,j]<-Valor
            Tab2[i,j]<-Ficha
            
            // Verificación de ganador
            aux_d1<-1; aux_d2<-1
            
            Para i<-1 Hasta 3 Hacer
                aux_i<-1; aux_j<-1
                aux_d1<-aux_d1*Tab1[i,i]
                aux_d2<-aux_d2*Tab1[i,4-i]
                
                Para j<-1 Hasta 3 Hacer
                    aux_i<-aux_i*Tab1[i,j]
                    aux_j<-aux_j*Tab1[j,i]
                FinPara
                
                Si aux_i=Objetivo O aux_j=Objetivo Entonces
                    Ganador<-Verdadero
                FinSi
            FinPara
            
            Si aux_d1=Objetivo O aux_d2=Objetivo Entonces
                Ganador<-Verdadero
            Sino
                TurnoJugador1<-~TurnoJugador1
            FinSi
            
        Sino
            
            Si Ganador Entonces
                Escribir "Ganador:"
                Si TurnoJugador1 Entonces
                    Escribir "Jugador 1!"
                Sino
                    Escribir "Jugador 2!"
                FinSi
            Sino
                Escribir "Empate!"
            FinSi
            
            Terminado<-Verdadero
            
        FinSi
        
    FinMientras
    
FinProceso
