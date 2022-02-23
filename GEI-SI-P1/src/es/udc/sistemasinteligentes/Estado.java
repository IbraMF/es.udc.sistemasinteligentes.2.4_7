package es.udc.sistemasinteligentes;

public abstract class Estado {
    /* El estado deberá sobreescribir estos métodos para mostrarse correctamente y permitir comparaciones. */

    @Override
    public abstract java.lang.String toString();
    /* Ejemplo:
        return "(" + posicionRobot + "," + posicionBasura + ')'
    */

    @Override
    public abstract boolean equals(Object obj);
    /* Ejemplo:
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadoAspiradora that = (EstadoAspiradora) o;

        if (posicionRobot != that.posicionRobot) return false;
        return posicionBasura == that.posicionBasura;
    */

    @Override
    public abstract int hashCode();
    /* Ejemplo:
        int result = posicionRobot.hashCode();
        result = 31 * result + posicionBasura.hashCode();
        return result;
    */
}
