package tiny1;

%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

int = int
bool = bool
real = real
true = true
false = false
or = or
and = and
not = not
string = string
null = null
proc = proc
if = if
then = then
else = else
endif = endif
while = while
do = do
endwhile = endwhile
call = call
record = record
array = array
of = of
pointer = pointer
new = new
delete = delete
read = read
write = write
nl = nl
var = var
type = type

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
parteEntera = {digitoPositivo}{digito}*
numeroEntero = {signo}?{parteEntera}
parteDecimal = \. {digito}* {digitoPositivo}
parteExponencial = (e|E) {numeroEntero}
signo = [\+,\-]

variable = {letra}({letra}|{digito}|_)*
numeroReal = {numeroEntero}({parteDecimal}|{parteExponencial}|{parteDecimal}{parteExponencial})
cadena = "[^",\b,\r,\n]"

operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = \/
parentesisApertura = \(
parentesisCierre = \)
corcheteApertura = \[
corcheteCierre = \]
llaveApertura = \{
llaveCierre = \}
igualAsignacion = \=
igualComparacion = \=\=
pcoma = \;
coma  = \,
menor = \<
menorIgual = \<\=
mayor = \>
mayorIgual = \>\=
distinto = \!\=
porCiento = \%
punto = \.
flecha = \-\>
ampersand = \&


separador = [ \t\r\b\n]
comentario = #[^\n]* 
%%
{separador}               {}
{comentario}              {}
{evalua}                  {return ops.unidad();}
{donde}                   {return ops.unidadDonde();}
{identificador}           {return ops.unidadId();}
{numeroEntero}            {return ops.unidadEnt();}
{numeroReal}              {return ops.unidadReal();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPAp();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{igual}                   {return ops.unidadIgual();} 
{coma}                    {return ops.unidadComa();}
[^]                       {ops.error();}  