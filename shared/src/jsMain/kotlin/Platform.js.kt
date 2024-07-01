class JSPlatform: Platform {
    override val name: String = "JS platform"
}

actual fun getPlatform(): Platform = JSPlatform()