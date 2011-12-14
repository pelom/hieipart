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
public class HieiPartTest {

	/** Arquivo **/
	private HieiPart hieiPart;
	
	/** Arquivo a ser particionado **/
	private IHieiFile hieiFile;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		hieiFile = new HieiFile("src/test/resources/teste.zip");
		hieiPart = new HieiPart();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		hieiPart = null;
	}

	/**
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRetalhacao() throws IOException {
		hieiPart.retalhar(hieiFile);
		
		Assert.assertEquals(true, new File(hieiFile.getPath() + HieiPart.EXT_INFO).exists());	
	}
}