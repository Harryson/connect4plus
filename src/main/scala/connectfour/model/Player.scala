 package connectfour.model 
 
 import connectfour.util.observer.IObserverWithArguments;
 
 trait Player extends IObserverWithArguments {
    var name: String
    
    def getGameField(): GameField
    
    def setGameField(gameField: GameField)
 }