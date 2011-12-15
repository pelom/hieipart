package br.pelommedrado.hieipart;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.pelommedrado.hieipart.impl.HieiFile;

/**
 * @author Andre Leite
 */
public class HieiTest {

	/** Arquivo **/
	private Hiei hieiPart;
	
	/** Arquivo a ser particionado **/
	private IHieiFile hieiFile;
	
	/** path do arquivo **/
	private String pathName;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		pathName = "src/test/resources/teste.tar.gz";
		hieiFile = new HieiFile(pathName);
		hieiPart = new Hiei();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		hieiPart = null;
	}

	@Test
	public void testCompor() throws Exception {
		hieiPart.compor(pathName);
		
		Assert.assertEquals(true, new File(hieiFile.getPath()).exists());
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRetalhacao() throws IOException {
		hieiPart.retalhar(hieiFile);
		
		Assert.assertEquals(true, new File(hieiFile.getPath() + Hiei.EXT_INFO).exists());	
	}
}