package program.game;

public enum GameSM {
    Setup {
        @Override
        public GameSM nextState() {
            return Ready;
        }

        @Override
        public String getState() {
            return "Setup";
        }

        @Override 
        public GameSM endGame() {
            return Ended;
        }

    },
    Ready {
        @Override
        public GameSM nextState() {
            return HostTurn;
        }

        @Override
        public String getState() {
            return "Ready";
        }

        @Override 
        public GameSM endGame() {
            return Ended;
        }
    },

    HostTurn {
        @Override
        public GameSM nextState() {
            return ClientTurn;
        }

        @Override
        public String getState() {
            return "HostTurn";
        }

        @Override 
        public GameSM endGame() {
            return Ended;
        }
    },
    ClientTurn {
        @Override
        public GameSM nextState() {
            return HostTurn;
        }

        @Override
        public String getState() {
            return "ClientTurn";
        }

        @Override 
        public GameSM endGame() {
            return Ended;
        }
    },

    Ended {
        @Override
        public GameSM nextState() {
            return this;
        }

        @Override
        public String getState() {
            return "Ended";
        }

        @Override 
        public GameSM endGame() {
            return Ended;
        }
    };

    public abstract GameSM nextState(); 
    public abstract GameSM endGame(); 
    public abstract String getState();
}
