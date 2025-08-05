package org.akwapos.app.models

enum class SlideAction {
    ContinuousDown, // Moves down/right continuously (resets to start)
    Bounce          // Moves back and forth between -distance and +distance
}