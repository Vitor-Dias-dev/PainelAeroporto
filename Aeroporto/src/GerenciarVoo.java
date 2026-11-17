import java.util.*;
import java.time.LocalTime;

public class GerenciarVoo {
    Scanner sc = new Scanner(System.in);
    private Voos[] voos;
    private int quantidade;
    private Status Status;

    public GerenciarVoo() {
        voos = new Voos[5];
        quantidade = 0;
    }

    public void iniciar() {

        int opcao;
        do {
            System.out.println("\n========== PAINEL DE VOOS ==========");
            System.out.println("1 - Cadastrar voo");
            System.out.println("2 - Listar voos");
            System.out.println("4 - Alterar status");
            System.out.println("5 - Excluir voo");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção válida: ");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) { // seleção do menu acima para cadastro dos voos

                case 1:
                    cadastrarVoo();
                    break;

                case 2:
                    listarVoos();
                    break;

                case 3:
                    alterarInformacao();
                    break;

                case 4:
                    alterarStatus();
                    break;

                case 5:
                    excluirVoo();
                    break;

                case 0:
                    System.out.println("Encerrando sistema.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        while (opcao != 0);
    }

    public void cadastrarVoo() {

        if (quantidade == voos.length) { // quantidade igual o tamanho do vetor
            System.out.println("Limite de voos atingido!");
            return;
        }

        System.out.print("Número do voo: ");
        String numeroVoo = sc.nextLine();

        System.out.print("Companhia aérea: ");
        String companhia = sc.nextLine();

        System.out.print("Horário de embarque: ");
        LocalTime horarioEmbarque = LocalTime.parse(sc.nextLine());

        System.out.print("Horário de saída: ");
        LocalTime horarioSaida = LocalTime.parse(sc.nextLine());
        while (horarioSaida.isBefore(horarioEmbarque) || horarioSaida.equals(horarioEmbarque)) {
            if (horarioSaida.isBefore(horarioEmbarque) || horarioSaida.equals(horarioEmbarque)) {
                System.out.println("O horário de saída não pode ser anterior ou igual ao horário de embarque!");
                System.out.print("Horário de saída: ");
                horarioSaida = LocalTime.parse(sc.nextLine());
                //if (horarioSaida.isBefore(horarioEmbarque) || horarioSaida.equals(horarioEmbarque)) {
                //       System.out.println("O horário de saída não pode ser anterior ou igual ao horário de embarque!");
            }
        }
        // }

        System.out.print("Destino: ");
        String destino = sc.nextLine();

        this.Status = Status.PREVISTO;

        Voos voo = new Voos(numeroVoo, companhia, destino, horarioEmbarque, horarioSaida);

        voos[quantidade] = voo;
        quantidade++; // adicionar a quantidade ao vetor

        System.out.println("Voo cadastrado com sucesso!");
    }

    // Listar voos
    public void listarVoos() {

        if (quantidade == 0) { // Se não tiver voo ele ele não terá nada para listar
            System.out.println("Nenhum voo cadastrado.");
            return;
        }

        for (int i = 0; i < quantidade; i++) {
            voos[i].exibirVoo();
        }
    }

    public void alterarInformacao(){
        System.out.print("Número do voo: ");
        String numeroVoo = sc.nextLine();
        int posicao = buscarVoo(numeroVoo);
        if (posicao == -1) {
            System.out.println("Voo não encontrado.");
            return;
        }



    }
    public void alterarStatus() {

        System.out.print("Número do voo: ");
        String numeroVoo = sc.nextLine();
        int posicao = buscarVoo(numeroVoo);
        if (posicao == -1) {
            System.out.println("Voo não encontrado.");
            return;
        }
        Status statusAtual = voos[posicao].getStatus(); // voos[posicao].getStatus(); percorre o vetor voos e pega o status autal dele e guarda na variável statusAtual.
        Status proximo = proximoStatus(statusAtual); // proximoStatus(statusAtual); cai no metodo com if/else e usa o valor guardado na variável statusAtual para iniciar a comparação

        if (proximo == null) {
            System.out.println("Este voo já decolou. Não há status a ser adicionado");
            return;
        }
        System.out.print("Confirma a mudança de Status (S/N)?: ");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("S")) { // confirmar se deseja realmente alterar o status
            voos[posicao].setStatus(proximo); // vai no vetor voos e define qual seria o próximo status de acordo com o metodo proximoStatus
            System.out.println("Status de voo atualizado!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private Status proximoStatus(Status atual) {
        if (atual == Status.PREVISTO) {
            return Status.CONFIRMADO;
        } else if (atual == Status.CONFIRMADO) {
            return Status.EMBARQUE_EM_CURSO;
        } else if (atual == Status.EMBARQUE_EM_CURSO) {
            return Status.EMBARQUE_ENCERRADO;
        } else if (atual == Status.EMBARQUE_ENCERRADO) {
            return Status.DECOLOU;
        }
        return null;

    }

    public void excluirVoo() {

        System.out.print("Número do voo: ");
        String codigo = sc.nextLine();

        int posicao = buscarVoo(codigo); // busca o codigo do voo para excluir

        if (posicao == -1) {
            System.out.println("Voo não encontrado.");
            return;
        }
        if (voos[posicao].getStatus() == Status.DECOLOU) { // Vai percorrer o vetor e procurar qual o número do voo, vai pegar o status pelo getStatus e vai comparar para ver se o status é decolou
            System.out.print("Confirma a exclusão de Status (S/N)?: ");
            String resposta = sc.nextLine();
//while(resposta) !!!!!!!!!! Tem que ver se você vai querer que ele fique repetindo para digitar algo com S OU N ou se, caso digite algo diferente disso, ele volta para o menu
            if (resposta.equalsIgnoreCase("S")) {
                for (int i = posicao; i < quantidade - 1; i++) { // Se o status for decolar ele vai realizar a exclusão do voo
                    voos[i] = voos[i + 1];
                }

                voos[quantidade - 1] = null;
                quantidade--;

                System.out.println("Voo excluído!");
            }
        } else {
            System.out.println("Operação não autorizada!");
        }
    }
    private int buscarVoo(String codigo) {

        for (int i = 0; i < quantidade; i++) {

            if (voos[i].getNumeroVoo().equals(codigo)) {
                return i;
            }
        }

        return -1;
    }
}
