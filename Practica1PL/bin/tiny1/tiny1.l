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
parteEntera = {digitoPositivo}{digito}* | 0
numeroEntero = {signo}?{parteEntera}
parteDecimal = \. ({digito}* {digitoPositivo} | 0)
parteExponencial = (e|E) {numeroEntero}
signo = [\+,\-]

variable = {letra}({letra}|{digito}|_)*
numeroReal = {numeroEntero}({parteDecimal}|{parteExponencial}|{parteDecimal}{parteExponencial})
cadena = \"[^\",\b,\r,\n]*\"

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
sepSec = \&\&


separador = [ \t\r\b\n]
comentario = #[^\n]* 
%%
{separador}               {}
{comentario}              {}
{int}                     {return ops.unidadInt();}
{real}                    {return ops.unidadReal();}
{bool}                  {return ops.unidadBool();}
{true}                  {return ops.unidadTrue();}
{false}                  {return ops.unidadFalse();}
{or}                  {return ops.unidadOr();}
{and}                  {return ops.unidadAnd();}
{not}                  {return ops.unidadNot();}
{string}                  {return ops.unidadString();}
{null}                  {return ops.unidadNull();}
{proc}                  {return ops.unidadProc();}
{if}                  {return ops.unidadIf();}
{then}                  {return ops.unidadThen();}
{else}                  {return ops.unidadElse();}
{endif}                  {return ops.unidadEndif();}
{while}                  {return ops.unidadWhile();}
{do}                  {return ops.unidadDo();}
{endwhile}                  {return ops.unidadEndwhile();}
{call}                  {return ops.unidadCall();}
{record}                  {return ops.unidadRecord();}
{array}                  {return ops.unidadArray();}
{of}                  {return ops.unidadOf();}
{pointer}                  {return ops.unidadPointer();}
{new}                  {return ops.unidadNew();}
{delete}                  {return ops.unidadDelete();}
{read}                  {return ops.unidadRead();}
{write}                  {return ops.unidadWrite();}
{nl}                  {return ops.unidadNL();}
{var}                  {return ops.unidadVar();}
{type}                  {return ops.unidadType();}

{numeroReal}              {return ops.unidadNumeroReal();}
{cadena}           {return ops.unidadCadena();}
{numeroEntero}            {return ops.unidadNumeroEntero();}
{variable}           {return ops.unidadVariable();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{parentesisApertura}      {return ops.unidadPApertura();}
{parentesisCierre}        {return ops.unidadPCierre();} 
{igualAsignacion}                   {return ops.unidadIgualAsig();}
{igualComparacion}                  {return ops.unidadIgualComp();} 
{coma}                    {return ops.unidadComa();}
{pcoma}                    {return ops.unidadPComa();}
{sepSec}           {return ops.unidadSepSec();}
{mayor}           {return ops.unidadMayor();}
{menor}           {return ops.unidadMenor();}
{mayorIgual}           {return ops.unidadMayorIg();}
{menorIgual}           {return ops.unidadMenorIg();}
{distinto}           {return ops.unidadDistinto();}

{porCiento}           {return ops.unidadPorCiento();}
{corcheteApertura}           {return ops.unidadCorApertura();}
{corcheteCierre}           {return ops.unidadCorCierre();}
{llaveApertura}           {return ops.unidadLlaveApertura();}
{llaveCierre}           {return ops.unidadLlaveCierre();}
{punto}           {return ops.unidadPunto();}
{flecha}           {return ops.unidadFlecha();}
{ampersand}           {return ops.unidadAmpersand();}
[^]                       {ops.error();}  