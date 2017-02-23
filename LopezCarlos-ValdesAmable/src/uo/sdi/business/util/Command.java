package uo.sdi.business.util;

public interface Command {

	public Object execute() throws BusinessException;
}
