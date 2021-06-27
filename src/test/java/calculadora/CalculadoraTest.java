package calculadora;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Classe para teste da calculadora")
public class CalculadoraTest {
	
	private Calculadora calc;
	
	@BeforeEach
	public void inicializa() {
		calc = new Calculadora();
	}
	
	@DisplayName("Testa a soma de dois números")
	@Test
	public void testSomaDoisNumeros() {
		int soma = calc.soma(4, 5);		
		Assertions.assertEquals(9, soma);		
	}

	@Test
	public void testSubtracaoDoisNumeros() {
		int sub = calc.subtracao(5, 4);
		Assertions.assertEquals(1, sub);
	}

	@Test
	public void testSubtracaoDoisNumerosResultadoNegativo() {
		int sub = calc.subtracao(4, 5);
		Assertions.assertEquals(-1, sub);
	}

	@Test
	public void testDivisaoDoisNumeros() {
		int divisao = calc.divisao(8, 4);
		assertEquals(2, divisao);
	}
	
	@Test
	public void testDivisaoPorZero() {
		try {
			int divisao = calc.divisao(8, 0);
			fail("Exceção não lançada");
		}catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}		
	}
	
	@Test
	public void testDivisaoPorZeroComAssertThrows() {
		assertThrows(ArithmeticException.class,
				() -> calc.divisao(8, 0));
	}

	@Test
	public void testMultiplacaoDoisNumeros(){
		int produto = calc.multiplicacao(3, 9);
		assertEquals(27, produto);
	}

	@Test
	public void testMultiplicacaoPorZero(){
		int p1 = calc.multiplicacao(0, 0);
		int p2 = calc.multiplicacao(0, 1);
		int p3 = calc.multiplicacao(0, 9);
		int p4 = calc.multiplicacao(0, 99);

		assertAll("Multiplicação por zero",
				() -> assertEquals(0, p1),
				() -> assertEquals(0, p2),
				() -> assertEquals(0, p3),
				() -> assertEquals(0, p4));

	}

	@Test
	public void testSomatoriaEntradaNegativa(){
		int somatoria = calc.somatoria(-10);
		assertEquals(0, somatoria);
	}

	@Test
	public void testSomatoriaEntradaZero(){
		int somatoria = calc.somatoria(0);
		assertEquals(0, somatoria);
	}

	@Test
	public void testSomatoriaEntrada(){
		int somatoria = calc.somatoria(5);
		assertEquals(15, somatoria);
	}

	@Test
	public void testPositivoEntradaNegativa(){
		boolean res = calc.ehPositivo(-10);
		assertFalse(res);
	}

	@Test
	public void testPositivoEntradaZero(){
		boolean res = calc.ehPositivo(0);
		assertTrue(res);
	}

	@Test
	public void testPositivoEntradaPositiva() {
		boolean res = calc.ehPositivo(10);
		assertTrue(res);
	}

	@Test
	public void testComparaNumerosIguais(){
		int c1 = calc.compara(0,0 );
		int c2 = calc.compara(-10,-10 );
		int c3 = calc.compara(10,10 );

		assertAll(
				() -> assertEquals(0, c1),
				() -> assertEquals(0, c2),
				() -> assertEquals(0, c3));
	}

	@Test
	public void testComparaNumerosDiferentes(){
		int c1 = calc.compara(0, 1);
		int c2 = calc.compara(1, 0);
		int c3 = calc.compara(0, -1);
		int c4 = calc.compara(-1, 0);

		assertAll(
				() -> assertEquals(-1, c1),
				() -> assertEquals(1, c2),
				() -> assertEquals(1, c3),
				() -> assertEquals(-1, c4)
		);
	}
}
