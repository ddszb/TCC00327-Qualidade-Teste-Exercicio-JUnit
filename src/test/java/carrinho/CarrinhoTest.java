package carrinho;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import produto.Produto;
import produto.ProdutoNaoEncontradoException;

public class CarrinhoTest {

    Carrinho carrinho;

    @BeforeEach
    public void inicializa() {
        carrinho = new Carrinho();
    }

    @Test
    public void testValorTotalCarrinhoVazio(){
        double valorTotal = carrinho.getValorTotal();
        Assert.assertEquals(0.0, valorTotal,0.01);
    }

    @Test
    public void testObterQuantidadeItemCarrinhoVazio(){
        int qtd = carrinho.getQtdeItems();
        Assert.assertEquals(0, qtd);
    }

    @Test
    public void testAdicionarItem(){
        Assert.assertEquals(0, carrinho.getQtdeItems());
        carrinho.addItem( new Produto("Produto A", 100));
        Assert.assertEquals(1, carrinho.getQtdeItems());
        carrinho.addItem( new Produto("Produto B", 100));
        Assert.assertEquals(2, carrinho.getQtdeItems());
    }

    @Test
    public void testRemoveItem() throws ProdutoNaoEncontradoException {
        carrinho.addItem( new Produto("Produto A", 100));
        Produto p2 = new Produto("Produto B", 50);
        carrinho.addItem( p2);
        Assert.assertEquals(2, carrinho.getQtdeItems());
        carrinho.removeItem(p2);
        Assert.assertEquals(1, carrinho.getQtdeItems());
    }

    @Test
    public void testRemoveItemItemNaoEncontrado(){
        carrinho.addItem( new Produto("Produto A", 100));
        carrinho.addItem( new Produto("Produto B", 100));

        Assert.assertEquals(2, carrinho.getQtdeItems());
        try{
            carrinho.removeItem(new Produto("Produto C", 30));
            Assert.fail();
        } catch (ProdutoNaoEncontradoException e) {
            Assert.assertEquals(2, carrinho.getQtdeItems());
        }
    }

    @Test
    public void testEsvaziaCarrinho(){
        carrinho.addItem( new Produto("Produto A", 100));
        carrinho.addItem( new Produto("Produto B", 100));
        Assert.assertEquals(2, carrinho.getQtdeItems());
        carrinho.esvazia();
        Assert.assertEquals(0, carrinho.getQtdeItems());
    }

    @Test
    public void testValorTotalCarrinhoCheio(){
        carrinho.addItem( new Produto("Produto A", 100));
        carrinho.addItem( new Produto("Produto B", 10));
        double valorTotal = carrinho.getValorTotal();
        Assert.assertEquals(110, valorTotal, 0.01);
    }

    @Test
    public void testInserirRemoverItemAlteraValorTotalCarrinho() {
        double valorTotal;
        valorTotal = carrinho.getValorTotal();
        Assert.assertEquals(0, valorTotal, 0.01);
        carrinho.addItem(new Produto("Produto A", 100));
        Produto p2 = new Produto("Produto B", 10);
        carrinho.addItem(p2);
        valorTotal = carrinho.getValorTotal();
        Assert.assertEquals(110, valorTotal, 0.01);
    }

    @Test
    public void testRemoverItemAlteraValorTotalCarrinho() throws ProdutoNaoEncontradoException{

        carrinho.addItem( new Produto("Produto A", 100));
        Produto p2 = new Produto("Produto B", 10);
        carrinho.addItem(p2);

        double valorTotal;
        valorTotal = carrinho.getValorTotal();
        Assert.assertEquals(110, valorTotal, 0.01);
        try{
            carrinho.removeItem(p2);
            valorTotal = carrinho.getValorTotal();
            Assert.assertEquals(100, valorTotal, 0.01);
        }catch (ProdutoNaoEncontradoException e){
            Assert.fail();
        }
        carrinho.esvazia();
        valorTotal = carrinho.getValorTotal();
        Assert.assertEquals(0, valorTotal, 0.01);
    }
}
