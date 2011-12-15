/**
 * 
 */
package br.pelommedrado.hieipart.impl;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.pelommedrado.hieipart.IHieiFile;
import br.pelommedrado.hieipart.IHieiParticionador;

/**
 * @author Andre Leite
 */
public class HieiParticionadorTest {

	/** particionador de arquivo **/
	private IHieiParticionador particionador;
	
	/** Arquivo a ser particionado **/
	private IHieiFile hieiFile;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hieiFile = new HieiFile("src/test/resources/teste.zip");
		particionador = new HieiParticionador(hieiFile);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		particionador = null;
	}

	/**
	 * Test method for {@link br.pelommedrado.hieipart.impl.HieiParticionador#particionar()}.
	 * @throws IOException 
	 */
	@Test
	public void testParticionar() throws IOException {
		particionador.particionar();
		
		Assert.assertEquals(3, hieiFile.getNumPart());
	}
}