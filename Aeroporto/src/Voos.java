    import java.time.LocalTime;

    public class Voos {

        private String numeroVoo;
        private String companhia;
        private String destino;
        private LocalTime horarioEmbarque;
        private LocalTime horarioSaida;
        private Status status;

        public Voos(String numeroVoo, String companhia, String destino, LocalTime horarioEmbarque, LocalTime horarioSaida) {

            this.numeroVoo = numeroVoo;
            this.companhia = companhia;
            this.destino = destino;
            this.horarioEmbarque = horarioEmbarque;
            this.horarioSaida = horarioSaida;
            this.status = Status.PREVISTO;
        }

        public String getNumeroVoo() {
            return numeroVoo;
        }

        public String getCompanhia() {
            return companhia;
        }

        public String getDestino() {
            return destino;
        }

        public LocalTime getHorarioEmbarque() {
            return horarioEmbarque;
        }
        public LocalTime getHorarioSaida() {
            return horarioSaida;
        }
        public Status getStatus() {
            return status;
        }

        public void setCompanhia(String companhia) {
            this.companhia = companhia;
        }

        public void setDestino(String destino) {
            this.destino = destino;
        }

        public void setHorarioEmbarque(LocalTime horarioEmbarque) {
            this.horarioEmbarque = horarioEmbarque;
        }
        public void setHorarioSaida(LocalTime horarioSaida) {
            this.horarioSaida = horarioSaida;
        }
        public void setStatus(Status status) { // puxar os valore da classe Status para definir o status do voo
            this.status = status;
        }

        public void exibirVoo() {
            System.out.println("==============================================");
            System.out.println("Número voo: " + numeroVoo);
            System.out.println("Companhia: " + companhia);
            System.out.println("Destino: " + destino);
            System.out.println("Horário: " + horarioEmbarque);
            System.out.println("Horário: " + horarioSaida);
            System.out.println("Status: " + status);
            System.out.print("==============================================");

        }
    }

