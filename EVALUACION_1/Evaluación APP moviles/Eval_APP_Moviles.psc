Algoritmo Estacionamiento
	
	Definir placa Como Cadena
	Definir tipo Como Cadena
	Definir minutos Como Entero
	Definir precio_minuto, precio_base, descuento, precio_final Como Real
	Definir horas, min_restantes Como Entero
	Definir porc_descuento Como Real
	
	Escribir "Ingrese la placa del vehículo:"
	Leer placa
	
	Escribir "Ingrese el tipo de vehículo (motocicleta / auto / camioneta):"
	Leer tipo
	
	Mientras tipo <> "motocicleta" Y tipo <> "auto" Y tipo <> "camioneta" Hacer
		Escribir "Tipo inválido. Ingrese: motocicleta, auto o camioneta:"
		Leer tipo
		
	FinMientras
	
	
	Escribir "Ingrese el tiempo en minutos (mayor a 0):"
	Leer minutos
	
	Mientras minutos <= 0 Hacer
	 Escribir "Tiempo inválido. Ingrese un valor mayor a 0:"
     Leer minutos 
	FinMientras
	
	Segun tipo Hacer
	"motocicleta":
	precio_minuto <- 0.02
	"auto":
	precio_minuto <- 0.25
	"camioneta":
	precio_minuto <- 0.30
	FinSegun
	
	precio_norm <- minutos * precio_minuto
	
	Si minutos > 180 Entonces
		porc_descuento <- 0.30
	SiNo
		Si minutos > 120 Entonces
			porc_descuento <- 0.20
		SiNo
			Si minutos > 60 Entonces
				porc_descuento <- 0.10
			SiNo
				porc_descuento <- 0
			FinSi
		FinSi
	FinSi
	
	descuento <- precio_norm * porc_descuento
	precio_final <- precio_norm - descuento
	
	horas <- trunc(minutos / 60)
	min_restantes <- minutos - (horas * 60)
	
	Escribir "----------------------------------------"
	Escribir "     RESUMEN DE ESTACIONAMIENTO"
	Escribir "----------------------------------------"
	Escribir "Placa:            ", placa
	Escribir "Tipo de vehículo: ", tipo
	Escribir "Tiempo:           ", horas, " horas y ", min_restantes, " minutos"
	Escribir "Precio norm:      S/ ", precio_norm
	Escribir "Descuento:        S/ ", descuento
	
	Escribir "Precio Final:    S/ ", precio_final
	Escribir "----------------------------------------"
FinAlgoritmo