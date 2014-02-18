package drops.ui

/**
 * A generic decorator for {@code TextLabel} class
 */
interface LabelDecorator {

    String apply( TextLabel label, String value )

}