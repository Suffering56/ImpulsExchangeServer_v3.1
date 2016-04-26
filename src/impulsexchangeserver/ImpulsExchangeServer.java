package impulsexchangeserver;

import impulsexchangeserver.options.OptionsHandler;

public class ImpulsExchangeServer {

    public static void main(String[] args) {
        OptionsHandler.readOptions();
        new FrameMain().setVisible(true);
    }
}
