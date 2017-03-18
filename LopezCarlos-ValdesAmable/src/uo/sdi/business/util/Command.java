package uo.sdi.business.util;

/**
 * Patrón Command
 * 
 * @author Amable y Carlos
 * 
 */
public interface Command {

	/**
	 * Método que nos permite ejecutar lógica de negocio de cualquier Action.
	 * 
	 * @return	Un objeto, puede ser el que queramos (TaskDTO, lista, etc.).
	 * @throws BusinessException	Si se cumple alguna irregularidad en la 
	 * lógica de negocio el sistema lanzará a capas superiores una excepción 
	 * de tipo BusinessException (Excepción de lógica de negocio).
	 */
	public Object execute() throws BusinessException;
}
