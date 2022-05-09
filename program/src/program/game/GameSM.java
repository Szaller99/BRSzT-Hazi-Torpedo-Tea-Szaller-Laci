package program.game;

public enum GameSM {
    Setup {
        @Override
        public GameSM nextState() {
            return Ready;
        }

        @Override
        public String get() {
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
        public String get() {
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
        public String get() {
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
        public String get() {
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
        public String get() {
            return "Ended";
        }

        @Override 
        public GameSM endGame() {
            return Ended;
        }
    };

    public abstract GameSM nextState(); 
    public abstract GameSM endGame(); 
    public abstract String get();
}
