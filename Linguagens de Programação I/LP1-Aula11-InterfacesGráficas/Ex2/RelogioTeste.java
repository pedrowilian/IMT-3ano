package Ex2;


class RelogioTeste {


    public static void main(String[] args) {
        Relogio relogio = new Relogio();

        for (int i = 0; i < 1440; i++) {
            relogio.ticTac();
            System.out.println(relogio.mostra());
        }

    }

}
