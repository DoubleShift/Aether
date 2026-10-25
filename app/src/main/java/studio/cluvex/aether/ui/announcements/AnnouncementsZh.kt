package studio.cluvex.aether.ui.announcements

/**
 * Simplified Chinese copy of the announcements in [Announcements].
 *
 * The Persian originals are editorial text written for an Iranian audience, so
 * they live in code rather than in `strings.xml`. This file mirrors the same
 * structure block for block, and [Announcements.localized] picks between the two
 * at runtime: Chinese builds must not show a Persian announcement.
 *
 * Keep the markup rules from [Announcements] intact: `**…**` strong, `*…*`
 * emphasis, `{…}` a left-to-right island around every Latin run.
 */
object AnnouncementsZh {

    private val v150 = Announcement(
        version = "1.5.0",
        shortTitle = "🚀 支持自定义配置与链路串联",
        blocks = listOf(
            AnnouncementBlock.Heading(
                "🚀 全新大功能：支持自定义配置（{Custom Configs}）",
                large = true,
            ),
            AnnouncementBlock.Paragraph(
                "各位老用户好！新版本加入了一个非常实用的能力——**自定义配置**。",
            ),
            AnnouncementBlock.Paragraph(
                "从现在起，你可以把自己的任意配置直接导入应用，不必再依赖 {v2rayNG} 或 " +
                    "{Npv Tunnel} 这类单独的应用：",
            ),
            AnnouncementBlock.Bullets(
                listOf(
                    "📁 **完整支持 NapsternetV 文件与链接（{.npvt}）**",
                    "⚡ **支持各类 {V2Ray} 配置**（含 {VLESS}、{VMess}、{Trojan}、" +
                        "{Shadowsocks}，以及很受欢迎的 {BPB} 面板配置）",
                    "📋 导入很简单：复制链接、选择文件，或直接拖拽（{Drag & Drop}）。",
                ),
            ),
            AnnouncementBlock.Warning(
                "⚠️ **导入配置前非常重要的一点：**\n" +
                    "添加配置之前，请务必确认该配置的服务器**在线、正常、可用**。如果服务器本身已经" +
                    "关停，应用不会变魔术，任何模式下都连不上！所以请先确认服务器是活的，再导入。",
            ),

            AnnouncementBlock.Heading("🔗 链路串联（{Chaining}）：绕开更重的封锁"),
            AnnouncementBlock.Paragraph(
                "这个功能最大的优势，是可以把**个人配置与应用内置的 Aether、Psiphon、Tor 引擎组合" +
                    "使用**：",
            ),
            AnnouncementBlock.Numbered(
                listOf(
                    "🛡️ **如果你的运营商封锁了 Psiphon 或 Tor：**\n" +
                        "可以把可用配置放在最前面（例如 {Config → Psiphon} 模式）。配置先帮你越过第一层" +
                        "封锁，出口就能拿到 Psiphon 的自由、不限量网络！",
                    "🔄 **如果你自己的配置被封了：**\n" +
                        "可以使用反向串联（例如 {Aether → Config} 或 {Tor → Config}）：先由 Aether 或 " +
                        "Tor 引擎绕开封锁，最终从你自己配置的干净 IP 出去。",
                    "🔀 **15 种串联组合（单跳、双跳、三跳）：**\n" +
                        "完全由你根据当前网络状况挑选最合适的链路。",
                ),
            ),
            AnnouncementBlock.Paragraph(
                "💡 *关于三跳模式的提示：* 在三层链路里（例如 {Config → Tor → Psiphon}），流量要经过" +
                    "三层加密与隧道，首次连接时间更长、延迟也更高；因此只在确实需要时使用三跳模式。",
                note = true,
            ),

            AnnouncementBlock.Heading("⏳ 首次连接请耐心等待！"),
            AnnouncementBlock.Paragraph(
                "无论是应用内置的 6 种连接模式，还是各种串联模式（尤其是含 **{Tor}** 的那些）：",
            ),
            AnnouncementBlock.Bullets(
                listOf(
                    "⏱️ **第一次连接可能需要 3 到 4 分钟！** 因为 Tor 与各引擎首次必须获取并缓存" +
                        "网络信息、目录与中继列表。请不要反复点按钮，耐心等一会儿。",
                    "⚡ **从第二次开始：** 由于信息已缓存，连接会快得多（通常**不到 1 分钟**）。",
                ),
            ),

            AnnouncementBlock.Heading("📶 运营商差异极大，以及线路调优经验"),
            AnnouncementBlock.Paragraph(
                "封锁强度与 {DPI} 干扰程度并不一致：{MCI}（{Hamrah-e Aval}）的网络状况与 " +
                    "{Irancell}、{TCI}（{Mokhaberat}）或私营运营商（如 {Shatel}、{Asiatech}）都不同，" +
                    "甚至不同地区、不同城市之间也有差异！",
            ),
            AnnouncementBlock.Bullets(
                listOf(
                    "✅ **我们的实测经验：** 在默认设置下（6 种主模式与各种串联），应用在 **{MCI} 与 " +
                        "{Asiatech}** 上都能正常连接；但在你所在的地区或运营商可能需要调整。",
                    "🛡️ **反 {DPI} 设置：** 如果普通模式连不上，进入应用的「传输与反-{DPI}」设置，" +
                        "尝试各种混淆方式。",
                ),
            ),

            AnnouncementBlock.Heading("🛠 完全连不上怎么办？"),
            AnnouncementBlock.Numbered(
                listOf(
                    "✈️ **重置 IP 与网络：** 手机开**飞行模式（{Airplane Mode}）**2 到 3 分钟；" +
                        "家用宽带则把 Wi-Fi 路由器完全断电 3 分钟再开，让 {IP} 变化。",
                    "🔄 **反复试错：** 在应用里切换不同的 {DPI} 相关选项，或换用其它可用的专属配置" +
                        "来串联。",
                    "🚪 **实话实说：** 如果你把所有模式、专属配置、{DPI} 设置和网络重连都试过了仍然" +
                        "不行，那很可能你所在区域的基站或电信机房对协议有严重的底层干扰，此时只能改用" +
                        "其它代理工具与 {VPN}。",
                ),
            ),
        ),
    )

    /** Newest first, mirroring [Announcements.all]. */
    val all: List<Announcement> = listOf(v150)

    fun forVersion(versionName: String): Announcement =
        all.firstOrNull { it.version == versionName } ?: all.first()
}
