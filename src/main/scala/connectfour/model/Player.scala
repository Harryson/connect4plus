 package connectfour.model
 
 trait Player2 {
    def name: String
    
    def getGameField(): GameField
    
    def setGameField(gameField: GameField)
 }