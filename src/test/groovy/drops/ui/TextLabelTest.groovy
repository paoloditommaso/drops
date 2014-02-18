package drops.ui

import spock.lang.Specification

/**
 *
 * @author Paolo Di Tommaso <paolo.ditommaso@gmail.com>
 */
class TextLabelTest extends Specification {

    def "test pad" () {

        expect:
        TextLabel.of('Hello').left().width(8).toString() == "Hello   "
        TextLabel.of('Hello').right().width(8).toString() == "   Hello"
        TextLabel.of('1234').number().width(8).toString() == "    1234"

    }

    def 'text align' () {

        /*
         * numbers are aligned automatically to the right
         */
        expect:
        new TextLabel('Hello').width(8).toString() == "Hello   "
        new TextLabel('1234').width(8).toString()  == "    1234"
        new TextLabel(1234).width(8).toString()    == "    1234"
        new TextLabel(1234).width(8).left().toString() == "1234    "

    }

    def 'text max' () {
        expect:
        new TextLabel('Hello world!   ').max(12).toString() == 'Hello world!'
        new TextLabel('Hello world!').max(12).toString() == 'Hello world!'
        new TextLabel('Hello world!').max(10).toString() == 'Hello wo..'
        new TextLabel('Hello world!').max(8).toString()  == 'Hello ..'
        new TextLabel('Hello world!').max(8).width(20).toString()  == 'Hello ..'

        new TextLabel(12345).max(6).toString() == '12345'
        new TextLabel(12345).max(5).toString() == '12345'
        new TextLabel(12345).max(4).toString() == '..45'

    }


    def "test apply decorator " () {

        when:
        def label = TextLabel.of( 'The cat eat the food' )
        def deco  = { obj, val -> "* ${val} *".toString() } as LabelDecorator
        label << deco

        then:
        label.toString() == "The cat eat the food"
        label.switchOn().toString() == "* The cat eat the food *"

    }

//    def "test add decorator" () {
//
//        when:
//        def label = TextLabel.of('The Fact cat') << AnsiStyle.style().bold()
//
//        then:
//        label.decorators.find { it instanceof AnsiStyle }
//        label.decorators.size() == 1
//
//    }

}
