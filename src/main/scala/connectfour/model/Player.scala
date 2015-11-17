 package connectfour.model

 import java.awt.Color
 import connectfour.util.observer.IObserverWithArguments;
 
 trait Player extends IObserverWithArguments {
   var name: String
   var color: Color
    
   def getGameField(): GameField
    
   def setGameField(gameField: GameField)
 }