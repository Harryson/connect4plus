package connectfour.model

import com.google.inject.AbstractModule
import connectfour.controller.MiddleSolver

/**
 * Created by maharr on 14.10.15.
 */
class SolverModule extends AbstractModule {
  protected def configure() = {
    bind(classOf[SolverPlugin]).to(classOf[MiddleSolver])
  }
}
