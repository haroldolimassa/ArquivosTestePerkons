import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Redimensionamento {
// Definindo a pasta de Origem , pasta de destino  
//e as dimensões da imagem  a ser gravada
    private static final String PASTA_ORIGEM = System.getProperty("user.home") + "/Documents/ImagEquip";
    private static final String PASTA_DESTINO = System.getProperty("user.home") + "/Documents/ImagEquip/Redimensionadas";
   
   
    private static final int NOVA_LARGURA = 600;
    private static final int NOVA_ALTURA = 400;
    private static final int TARJA_ALTURA_PRETA = 60;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitação do código do equipamento para usuário , 
        //Se for diferente de 10 caracteres retorna para o ponto anterior 
        System.out.print("Forneça o Código do Equipamento: ");
        String codigoEquipamento = scanner.nextLine();
        
        //O codigo do equipamento String com 10 caracteres
        if (codigoEquipamento.length() != 10) {
            System.err.println("O código do equipamento deve ter exatamente 10 caracteres.");
            return;
        }
//Instanciamento da variável pasta Origem e Destino
        File pastaOrigem = new File(PASTA_ORIGEM);
        File pastaDestino = new File(PASTA_DESTINO);
////////////////////////////////////////////////////////////////////

        // Criar a PASTA_DESTINO se NÃO existir cria pastaDestino 
        if (!pastaDestino.exists()) {
            pastaDestino.mkdirs();
        }
/////////////////////////////////////////////////////////////////////
        // Percorre a lista de arquivos da pasta origem e subdiretórios segue para condição
        // Processar aqueles que são imagens ImagemFile faz a verificação 
        for (File arquivo : pastaOrigem.listFiles()) {
            if (arquivo.isFile() && ImagemFile(arquivo)) {
                try {
        
                    BufferedImage imagemOriginal = ImageIO.read(arquivo);
                    BufferedImage imagemRedimensionada = redimensionarImagem(imagemOriginal);
                    
                    // Gera o novo nome do arquivo
                    String novoNome = gerarNomeArquivo(codigoEquipamento);
                    // ImageIO.write(imagemRedimensionada, "png", new File(pastaDestino, novoNome));
                    ImageIO.write(imagemRedimensionada, "jpg", new File(pastaDestino, novoNome));
                    
                    System.out.println("Imagem redimensionada e renomeada: " + novoNome);


                } catch (Exception e) {
                    System.err.println("Erro ao processar a imagem: " + arquivo.getName());
                    e.printStackTrace();
                }
            }
        }

        scanner.close();
    }

    // Verifica se o arquivo é uma imagem (segue extensoes de imagens permitidas no programa)
    private static boolean ImagemFile(File arquivo) {
        String[] extensoesPermitidas = { "jpg", "jpeg", "png", "gif", "bmp" };
        for (String ext : extensoesPermitidas) {
            if (arquivo.getName().toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    // Redimensiona a imagem e adiciona uma tarja preta na parte inferior
    private static BufferedImage redimensionarImagem(BufferedImage imagemOriginal) {
        BufferedImage imagemRedimensionada = new BufferedImage(NOVA_LARGURA, NOVA_ALTURA + TARJA_ALTURA_PRETA, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = imagemRedimensionada.createGraphics();

        // Redimensionar a imagem original
        g.drawImage(imagemOriginal.getScaledInstance(NOVA_LARGURA, NOVA_ALTURA, Image.SCALE_SMOOTH), 0, 0, null);

        // Adicionar tarja preta
        g.setColor(Color.BLACK);
        g.fillRect(0, NOVA_ALTURA, NOVA_LARGURA, TARJA_ALTURA_PRETA);

        g.dispose();
        return imagemRedimensionada;
    }
////////////////////////////////////////
    // Gera o novo nome do arquivo baseado como no enunciado data,codigo do equipamento,sequencia
     
    private static String gerarNomeArquivo(String codigoEquipamento) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dataHora = sdf.format(new Date());

        // Contagem de imagens e seguir com a sequencia  
        // Irá definir a sequencia que será renomeada a imagem 
        int seqImagem = contarImagensExistentes();

        // Formato do novo nome gravado
        return String.format("%s-%s-%03d.jpg", dataHora, codigoEquipamento, seqImagem);
    }
//////////////////////////////////////////////////////////////////
    // Conta a quantidade de imagens já existentes na pasta de destino
    private static int contarImagensExistentes() {
        File pastaDestino = new File(PASTA_DESTINO);
        File[] arquivos = pastaDestino.listFiles((dir, name) -> name.endsWith(".jpg"));
        return (arquivos != null) ? arquivos.length + 1 : 1; // Começa a contagem em 1
    }
}
