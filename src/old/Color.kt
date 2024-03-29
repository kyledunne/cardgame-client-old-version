package old

import org.lwjgl.opengl.GL11

class Color @JvmOverloads constructor(var r: Float, var g: Float, var b: Float, var a: Float = 1f) : Style {
    init {
        clamp()
    }

    constructor(r: Int, g: Int, b: Int, a: Int) : this(r / 255f, g / 255f, b / 255f, a / 255f)

    constructor(r: Int, g: Int, b: Int) : this(r / 255f, g / 255f, b / 255f, 1f)

    constructor(color: Color) : this(color.r, color.g, color.b, color.a)

    @JvmOverloads constructor(r: Double, g: Double, b: Double, a: Double = 1.0) : this(r.toFloat(), g.toFloat(), b.toFloat(), a.toFloat())

    override fun draw(rectangle: Rectangle) {
        if (this.a > 0) {
            glColor4f(this)
            GL11.glBegin(GL11.GL_POLYGON)
            GL11.glVertex2f(rectangle.absoluteX, rectangle.absoluteY)
            GL11.glVertex2f(rectangle.absoluteX + rectangle.width, rectangle.absoluteY)
            GL11.glVertex2f(rectangle.absoluteX + rectangle.width, rectangle.absoluteY + rectangle.height)
            GL11.glVertex2f(rectangle.absoluteX, rectangle.absoluteY + rectangle.height)
            GL11.glEnd()
        }
    }

    // A bunch of utility methods

    /** MAKES THE TEXTURE GO ALL THIS COLOR AND OOH ITS COOL  */
    fun bind() {
        org.newdawn.slick.Color(r, g, b).bind()
    }

    fun adjust(r: Int, g: Int, b: Int) {
        this.r += r / 255f
        this.g += g / 255f
        this.b += b / 255f
        clamp()
    }

    fun adjust(r: Int, g: Int, b: Int, a: Int) {
        this.r += r / 255f
        this.g += g / 255f
        this.b += b / 255f
        this.b += a / 255f
        clamp()
    }

    fun adjust(a: Int) {
        this.a += a / 255f
        clamp()
    }

    fun adjust(r: Float, g: Float, b: Float) {
        this.r += r
        this.g += g
        this.b += b
        clamp()
    }

    fun adjust(r: Float, g: Float, b: Float, a: Float) {
        this.r += r
        this.g += g
        this.b += b
        this.b += a
        clamp()
    }

    fun adjust(a: Float) {
        this.a += a
        clamp()
    }

    fun invert() {
        this.r = 1 - r
        this.g = 1 - g
        this.b = 1 - b
    }

    private fun clamp() {
        if (r > 1)
            r = 1f
        else if (r < 0)
            r = 0f
        if (g > 1)
            g = 1f
        else if (g < 0)
            g = 0f
        if (b > 1)
            b = 1f
        else if (b < 0)
            b = 0f
        if (a > 1)
            a = 1f
        else if (a < 0)
            a = 0f
    }

    companion object {
        val BLACK = Color(0f, 0f, 0f)
        val WHITE = Color(1f, 1f, 1f)
        val TRANSPARENT_BLACK = Color(0f, 0f, 0f, 0f)
        val TRANSPARENT = TRANSPARENT_BLACK
        val TRANSPARENT_WHITE = Color(1f, 1f, 1f, 0f)
        val RED = Color(1f, 0f, 0f)
        val GREEN = Color(0f, 1f, 0f)
        val DARK_GREEN = Color(.0f, .5f, .0f)
        val BLUE = Color(0f, 0f, 1f)
        val LIGHT_PURPLE = Color(1f, 0f, 1f)
        val CYAN = Color(0f, 1f, 1f)
        val YELLOW = Color(1f, 1f, 0f)
        val ORANGE = Color(1f, 165 / 255f, 0f)
        val DARK_ORANGE = Color(1f, 200 / 255f, 35f)
        val DARK_GREY = Color(50, 50, 50)
        val DARK_BLUE = Color(12, 0, 200)
        val GRAY = Color(.7f, .7f, .7f)
        val BROWN = Color(139, 90, 0)
        val CARROT = Color(237, 145, 33)
        val GREEN_GRASS = Color(.3f, .9f, .3f)
        val GREEN_GRASS_ALT = Color(.2f, 1f, .2f)

        fun adjustAColorsAlpha(color: Color, newAlpha: Float): Color {
            return Color(color.r, color.g, color.b, newAlpha)
        }

        fun adjustAColor(color: Color, lightnessAdjustment: Float): Color {
            return Color(color.r + lightnessAdjustment, color.g + lightnessAdjustment, color.b + lightnessAdjustment, color.a)
        }

        fun adjustAColor(color: Color, rAdjustment: Float, gAdjustment: Float, bAdjustment: Float, aAdjustment: Float = 0f): Color {
            return Color(color.r + rAdjustment, color.g + gAdjustment, color.b + bAdjustment, color.a + aAdjustment)
        }

        fun glClearColor(color: Color) {
            GL11.glClearColor(color.r, color.g, color.b, color.a)
        }

        fun glColor4f(color: Color) {
            GL11.glColor4f(color.r, color.g, color.b, color.a)
        }

        fun randomColor(): Color {
            return Color(Util.RAND.nextInt(), Util.RAND.nextInt(255), Util.RAND.nextInt(255))
        }

        fun randomNonOpaqueColor(): Color {
            return Color(Util.RAND.nextInt(), Util.RAND.nextInt(255), Util.RAND.nextInt(255), Util.RAND.nextInt(255))
        }

        fun invertedColor(color: Color): Color {
            return Color(1 - color.r, 1 - color.g, 1 - color.b, color.a)
        }

        fun isCloserToBlack(color: Color): Boolean {
            return color.r + color.g + color.b < 1.5
        }
    }
}
