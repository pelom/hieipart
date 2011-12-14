package br.pelommedrado.hieipart.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.pelommedrado.hieipart.IHieiFile;
import br.pelommedrado.hieipart.IHieiOutputStream;
import br.pelommedrado.hieipart.IHieiPart;
import br.pelommedrado.hieipart.IHieiParticionador;

/**
 * @author Andre Leite
 */
public class HieiOutputStreamTest {

	@Test
	public void testOutputPart() throws IOException {
		IHieiFile hieiFile = new HieiFile("src/test/resources/teste.zip");
		IHieiParticionador particionador = new HieiParticionador(hieiFile);

		List<IHieiPart> lista = particionador.particionar();
		IHieiOutputStream out = new HieiOutputStream();
		
		try {
			Assert.assertEquals(lista.get(0).getLen(), out.write(lista.get(0)));	
			Assert.assertEquals(lista.get(1).getLen(), out.write(lista.get(1)));	
			Assert.assertEquals(lista.get(2).getLen(), out.write(lista.get(2)));	
		} finally {
			out.close();
		}
		
		Assert.assertEquals(true, new File(lista.get(0).getNome()).exists());	
		Assert.assertEquals(lista.get(0).getLen(), new File(lista.get(0).getNome()).length());	
		
		Assert.assertEquals(true, new File(lista.get(1).getNome()).exists());	
		Assert.assertEquals(lista.get(1).getLen(), new File(lista.get(1).getNome()).length());	
		
		Assert.assertEquals(true, new File(lista.get(2).getNome()).exists());	
		Assert.assertEquals(lista.get(2).getLen(), new File(lista.get(2).getNome()).length());
	}
}