<p align="center">
  <img src="docs/ads.png" alt="Aether — 一键自由" width="100%">
</p>

<h1 align="center">Aether Mobile</h1>

<p align="center">
  一款深色的 <b>Material You</b> Android VPN 客户端，把 Aether 引擎、Tor、Psiphon
  和 Xray <b>内嵌在应用里</b>。
</p>

<p align="center">
  <a href="README.md">🇬🇧 English</a> · <a href="README.fa.md">🇮🇷 فارسی</a> · 简体中文
</p>

<p align="center">
  <img alt="version" src="https://img.shields.io/badge/version-1.5.0-8B5CF6?style=flat-square">
  <img alt="engine core" src="https://img.shields.io/badge/engine%20core-2.3.0-22D3EE?style=flat-square">
  <img alt="min Android" src="https://img.shields.io/badge/Android-8.0%2B-3DDC84?style=flat-square">
  <img alt="license" src="https://img.shields.io/badge/license-AGPL--3.0-F59E0B?style=flat-square">
</p>

---

> 本文件是 1.5.0 版英文 README 的中文译本，并额外说明了本分支新增的**简体中文界面**。

## 目录

- [概述](#概述)
- [功能](#功能)
- [连接模式](#连接模式)
- [自定义配置（V2Ray / Xray / NapsternetV）](#自定义配置v2ray--xray--napsternetv)
- [快速上手](#快速上手)
- [设置说明](#设置说明)
- [快捷设置磁贴与桌面小部件](#快捷设置磁贴与桌面小部件)
- [与其他设备共享 VPN](#与其他设备共享-vpn)
- [Gemini AI 助手](#gemini-ai-助手)
- [隐私：你的流量去了哪里](#隐私你的流量去了哪里)
- [安全审计](#安全审计)
- [更新与应用签名](#更新与应用签名)
- [构建](#构建)
- [系统要求](#系统要求)
- [简体中文界面](#简体中文界面)
- [更新日志](#更新日志)
- [致谢与许可](#致谢与许可)

## 概述

Aether Mobile 在手机上运行 Aether 引擎，并拉起 Android 的 `VpnService`。内置的隧道核心
（`hev-socks5-tunnel`）把 VPN 接口上的每个数据包转发进引擎的本地 SOCKS5 代理，因此不需要第二个
应用（比如 v2rayNG）。

```text
你的应用 ─► Android VPN (TUN) ─► hev-socks5-tunnel ─► 127.0.0.1:1819 ─► Aether 引擎 ─► 互联网
```

引擎连接 Cloudflare WARP（WireGuard 或 MASQUE），还可以叠加 Tor（arti，内建于引擎）和 Psiphon
作为更多跳数。你自己的 V2Ray / Xray / NapsternetV 配置运行在随包附带的 Xray-core 里，可以单独使用，
也可以与引擎串联。

## 功能

- **Material You 深色界面**（Jetpack Compose），Android 12+ 支持动态取色；提供英语、简体中文和波斯语
  （波斯语自动 RTL）。
- **六种引擎模式：** Aether、`Aether → Psiphon`、Tor、`Aether → Tor`、`Tor → Psiphon`、`Tor → Aether`。
- **自定义配置：** VLESS、VMess、Trojan、Shadowsocks、SOCKS、WireGuard 和 NapsternetV（`.npvt`），
  可单独使用，也可与引擎组成 14 种串联链路。
- **智能协议选择**：测量网络质量，在纯 Aether 下同时竞速两条路径，并记住每个网络上有效的选择。
- **完整的引擎选项：** 协议（Smart / MASQUE / WireGuard / Gool / MASQUE×2）、扫描模式、IP 版本、
  混淆（Noize）、手动端点或扫描范围、keepalive、MTU、TLS 分片、ECH、MASQUE over HTTP/2、快速重连、
  Tor 网桥、Psiphon 出口国家。
- **路由能力：** 按应用分流、域名/IP 规则（走隧道 / 直连 / 拦截）、智能路由列表、kill switch、
  IPv6 泄漏防护、代理模式。
- **Cloudflare Zero Trust** 接入（服务令牌、邮箱验证码或注册令牌）。
- **VPN 共享**：通过 Wi-Fi 或热点共享（HTTP、SOCKS5 与 PAC），并为其他设备提供配置引导页。
- **快捷设置磁贴**和**桌面小部件**。
- **可选的 Gemini AI 助手**，用于设置建议、功能解释与对话。
- **本地加密的诊断日志与设置**，使用 Android Keystore 密钥加密。

## 连接模式

| 模式 | 路径 | 适用场景 |
|------|------|-------------|
| **Aether** | 手机 → WARP | 默认；最快 |
| **Aether → Psiphon** | 手机 → WARP → Psiphon | 需要境外出口 IP 或指定出口国家 |
| **Tor** | 手机 → Tor | 需要 Tor 的匿名性（仅 TCP；DNS 在 Tor 内解析） |
| **Aether → Tor** | 手机 → WARP → Tor | 所在网络封锁 Tor；借 WARP 抵达 Tor |
| **Tor → Psiphon** | 手机 → Tor → Psiphon | 前面套 Tor，出口走 Psiphon |
| **Tor → Aether** | 手机 → Tor → WARP | 经 Tor 抵达 WARP 出口 |

## 自定义配置（V2Ray / Xray / NapsternetV）

打开**设置 → 自定义配置**并开启该分区。关闭时（默认）每个连接都使用上面的引擎模式。

### 导入

- **从剪贴板粘贴：** 单条或多条链接、夹在长文本里的链接（例如 Telegram 消息）、base64 订阅、
  `https://` 订阅地址，或完整的 Xray JSON 配置。
- **选择文件：** `.npvt`、`.txt`、`.json` 或二维码图片。
- **用相机扫码**，或**从图片中识别二维码**。

支持的协议：VLESS（含 REALITY 与 XTLS Vision）、VMess、Trojan、Shadowsocks、SOCKS 与 WireGuard。
传输方式：TCP/raw、WebSocket、gRPC、HTTPUpgrade、XHTTP 与 mKCP，可搭配 TLS 或 REALITY。

**NapsternetV（`.npvt` 文件与 `npvt://` 链接）** 在设备端解密，里面的每台服务器都会变成独立配置。
作者的限制被保留：锁定配置可以使用、串联、改名和删除，但服务器与密钥永不显示；加密文件会要求输入密码；
到期时间与"仅移动数据"会被强制执行；绑定设备的文件会被拒绝。

### 管理配置

每个已保存的配置都可以选中、编辑、改名、复制为链接、测试和删除。**测试延迟**是真实延迟测试（与
v2rayNG 相同）：在 Xray-core 里启动该配置，并*经它*发出一次 HTTP 请求（`generate_204`），
因此 REALITY、uTLS 指纹、传输方式与配置中的凭证都会被实际检验。结果以毫秒显示；失败显示 `-1`
并给出诊断：**无响应**（服务器接受 TCP 但配置拿不到回应：密钥错误或过期，或协议被过滤）、
**服务器关闭 / 不可达**、**被过滤**（黑洞或被丢弃的 TCP）或**配置无效**。**全部测试**可一次性检查
所有配置。每个结果都会写入诊断日志。配置以加密形式存储。

### 串联

配置可以放在引擎前面（引擎经它拨号），也可以放在引擎出口之后（配置经引擎拨号）：

| 单跳 | 双跳 | 三跳 |
|---|---|---|
| `配置` | `配置 → Aether` · `Aether → 配置` | `配置 → Aether → Psiphon` · `配置 → Aether → Tor` |
| | `配置 → Psiphon` · `Psiphon → 配置` | `配置 → Tor → Psiphon` · `配置 → Tor → Aether` |
| | `配置 → Tor` · `Tor → 配置` | `Aether → Psiphon → 配置` · `Aether → Tor → 配置` |
| | | `Tor → Psiphon → 配置` · `Tor → Aether → 配置` |

配置始终位于链路的一端。主页会显示当前运行的链路，例如 `VLESS → Aether(WireGuard)` 或 `Npvt → Tor`。

提示：

- `配置 → Tor …` 类链路首次连接需要几分钟，因为 Tor 要经该配置下载目录；之后的连接会复用。
- `Psiphon → 配置` 需要 Psiphon 自身能从你的网络连上它的网络。如果单独用 Psiphon 连不上，
  请改用 `配置 → Psiphon`。

## 快速上手

1. 安装 APK（选哪个见[构建](#构建)）并打开应用。
2. 点连接按钮。Android 会询问一次 VPN 权限。
3. 主页的链路行会显示当前生效的路径；有流量流过时会出现勾号。
4. 在主页的模式选择器里切换模式，用右上角的调节按钮或侧边菜单 → **设置**做细调。

## 设置说明

| 设置项 | 作用 |
|---------|--------------|
| **协议** | Smart（推荐）、MASQUE、WireGuard、Gool（MASQUE 之上的 WireGuard，或 WireGuard 之上的经典 WireGuard）、MASQUE×2 |
| **扫描模式** | 端点搜索策略：Turbo、Balanced、Thorough、Stealth、Ironclad |
| **IP 版本** | WARP 端点使用 IPv4、IPv6 或两者 |
| **混淆（Noize）** | 关闭、Light、Firewall、Balanced、GFW、Aggressive |
| **手动端点 / 扫描范围** | 固定某个端点，或把扫描限制在自己的范围 |
| **MTU、keepalive** | 隧道包大小与保活间隔 |
| **TLS 分片、ECH、MASQUE over HTTP/2** | 面向过滤 TLS 或 QUIC 的网络 |
| **Tor 网桥** | 自动（只在裸 Tor 失败时用网桥）、总是、关闭 |
| **Psiphon 出口国家** | 以 Psiphon 结尾的模式的出口国家 |
| **分流** | 只让选中的应用走隧道，或让除选中的应用外全部走隧道 |
| **路由规则** | 将域名 / IP 段走隧道、直连或拦截 |
| **Kill switch / IPv6 泄漏防护** | 阻止隧道之外的流量 |
| **代理模式** | 不开 VPN 接口，仅把引擎作为本地代理运行 |
| **Zero Trust** | 加入 Cloudflare Zero Trust 组织而不是消费者版 WARP |
| **核心日志级别** | *诊断*里引擎日志的详细程度 |

每个选项都有信息图标，解释它的作用。

## 快捷设置磁贴与桌面小部件

1. 下拉快捷设置，点编辑（铅笔）按钮，把 **Aether** 磁贴拖进你的磁贴里。
2. 点磁贴连接，再点一次断开。首次连接必须从应用内发起，以便 Android 弹出 VPN 权限对话框。

桌面小部件显示连接状态，单击即可连接或断开。

## 与其他设备共享 VPN

1. 先连上 VPN。
2. 侧边菜单 → **共享 VPN** → 选择**家庭 Wi-Fi**或**移动热点**并开启共享。
3. 在其他设备上使用应用里显示的地址：
   - **HTTP 代理：** `<手机 IP>:10809`
   - **SOCKS5 代理：** `<手机 IP>:10808`
   - 使用应用里显示的用户名和代理密码登录
   - 或者在该设备的浏览器里打开 `http://<手机 IP>:10811/`，按步骤配置
     （Windows、macOS、Android、iPhone、Firefox、电视）并获取 PAC 地址。
4. 在其他设备上访问 `http://aether.check/` 可确认它正在走隧道。

> ⚠️ 共享开启时，同一网络内的设备都能访问该代理。请只在可信网络中使用；
> **生成新密码**会吊销旧访问权限。VPN 断开时共享会停止。

## Gemini AI 助手

可选功能，在添加密钥前保持关闭。

1. 从 [Google AI Studio](https://aistudio.google.com/apikey) 获取免费密钥。
2. **设置 → 助手** → 粘贴密钥 → **测试 API 连接** → 选择模型。
3. 可用于：
   - **设置顾问：** 读取本次会话日志的脱敏片段并给出设置建议；在你点**应用**之前不会做任何改动。
   - 每个设置项旁的 **AI 图标**，用你的语言解释该设置。
   - **对话**（助手 → 对话，或主页上的 AI 按钮）。

密钥以加密形式存放在 Android Keystore 中。发送内容里会移除凭证与公网 IP 地址。助手只能修改调优类
选项（协议、扫描模式、混淆、MTU、时间参数等）；它永远无法修改模式、路由、分流、共享或 Zero Trust
凭证。改动在下次连接时生效。

AI 请求会走隧道，因此只有在 `Aether → Psiphon` 模式下连接时助手才可用（Google 的 AI 端点会拒绝
WARP 出口地址）。

## 隐私：你的流量去了哪里

- 流量在手机上就被加密（WireGuard 或 MASQUE），并从 Cloudflare WARP 离开；链路中有 Tor 或 Psiphon
  时，则从它们的网络离开；使用自定义配置时，从该配置的服务器离开。
- 应用没有账号、没有分析统计、没有开发者服务器。
- 与任何 VPN 一样，出口网络的运营方可以看到非端到端加密的流量（HTTPS 仍然是加密的）。
- 诊断日志只保存在手机上，并且是加密的。

细节：[docs/ZERO_TRUST.md](docs/ZERO_TRUST.md)、
[docs/SECURITY_AUDIT_1.5.0.md](docs/SECURITY_AUDIT_1.5.0.md)。

## 安全审计

1.5.0 的安全审计得分 **92 / 100**（十个加权领域，分制与 1.4.0 相同）；完整报告提供英文与波斯语：
[docs/SECURITY_AUDIT_1.5.0.md](docs/SECURITY_AUDIT_1.5.0.md)。

| 领域 | 得分 |
| --- | ---: |
| 密钥与凭证管理 | 95 |
| 密码学、TLS 与抗 MitM | 96 |
| 数据泄漏风险（DNS、IPv6、WebRTC、隧道绕过） | 90 |
| 局域网共享暴露面 | 86 |
| 本地静态存储 | 93 |
| 权限与系统配置 | 97 |
| 日志与诊断 | 97 |
| 代码质量与网络配置 | 88 |
| 设备端暴露（屏幕、剪贴板） | 95 |
| 供应链与构建完整性 | 80 |

- **DNS、IPv6、WebRTC：** DNS 服务器与 `::/0` 都被路由进隧道（IPv6 防护默认开启，且在串联中强制）；
  Tor 模式使用 TCP 承载 DNS 并丢弃 UDP；WebRTC 的 STUN 从隧道出口离开。代理模式与被分流排除的
  应用按设计会绕过隧道。
- **1.5.0 已修复：** 移除了 ping 探针"信任一切"的 TLS 检查；Xray 日志行不再暴露锁定 NPVT 配置的
  服务器或 UUID；已连接时订阅下载走隧道；*复制链接*被标记为敏感内容。
- **尚未解决：** 串联使用的本地 `127.0.0.1` SOCKS 端口没有密码；局域网共享默认无密码；R8 未开启。
- 静态审计加主机侧测试；仍建议在手机上做一次抓包验证。想要最强的 kill switch，请开启 Android 的
  *常开 VPN* 与*禁止无 VPN 连接*。

## 更新与应用签名

只有当新旧 APK 使用同一个密钥签名时，新 APK 才能覆盖安装。CI 用一把稳定密钥为每次发布签名：

- 当仓库里设置了以下 Secrets 时使用你自己的 keystore（推荐）：

  | Secret | 含义 |
  |--------|---------|
  | `KEYSTORE_BASE64` | `.jks` keystore 的 base64 编码（`base64 -w0 my-release-key.jks`） |
  | `KEYSTORE_PASSWORD` | keystore 密码 |
  | `KEY_ALIAS` | 密钥别名 |
  | `KEY_PASSWORD` | 密钥密码 |

- 否则使用仓库内保留的 CI keystore `.github/ci-keystore.jks.b64`。请保留该文件；删除它会生成新密钥，
  下次更新就需要卸载重装。

提交到公开仓库的 keystore 只能保证可更新性，不能保证真实性。参见 [docs/SIGNING.md](docs/SIGNING.md)
与 [docs/PLAY_PROTECT.md](docs/PLAY_PROTECT.md)。

## 构建

### 在 GitHub Actions 上（无需本地环境）

1. 把这个目录推到 GitHub 仓库并启用 Actions。
2. 每次推送到 `main` 都会构建并测试应用。发版时推送一个 tag：

   ```bash
   git tag v1.5.0
   git push origin v1.5.0
   ```

3. 发布里包含三个 APK；发布说明文本来自 `.github/release-notes.md`。

| 文件 | 适用 |
|------|-----|
| `Aether-1.5.0-arm64-v8a.apk` | 几乎所有现代手机（64 位 ARM） |
| `Aether-1.5.0-armeabi-v7a.apk` | 较老的 32 位手机 |
| `Aether-1.5.0-universal.apk` | 任何 ARM 手机 —— 不确定就选这个 |

### 原生组件

- `scripts/fetch-natives.sh` 拉取 `hev-socks5-tunnel`。
- `scripts/build-natives.sh` 用 `ndk-build` 构建 `libhev-socks5-tunnel.so`，用 `cargo-ndk` 构建
  Aether 引擎（`native/aether`，含 Tor），并从官方发布安装 Xray-core 为 `libxray.so`（以 SHA-256 校验）。
- CI 会拒绝缺少任一受支持 ABI 的原生组件的 APK。
- `scripts/sync-core.sh` 升级引擎内核并重新应用应用的引擎补丁（`patches/`）。

### 本地构建

JDK 17、Android SDK 35、NDK 26.3、带 `cargo-ndk` 的 Rust、Go 1.22 与 Gradle 8.9：

```bash
bash scripts/build-natives.sh
gradle :app:testReleaseUnitTest
gradle :app:assembleRelease
```

发布构建需要 keystore（`scripts/generate-keystore.sh`，见 [docs/SIGNING.md](docs/SIGNING.md)）。

## 系统要求

- Android 8.0（API 26）或更高，ARM（`arm64-v8a` 或 `armeabi-v7a`）。
- 权限：VPN（Android 标准授权）、通知（连接状态）、相机（仅在扫描二维码时）。

## 简体中文界面

本分支在上游 1.5.0 的基础上新增了简体中文本地化，除文档翻译外只有四处改动：

| 位置 | 改动 |
|------|------|
| `app/src/main/res/values-zh/strings.xml` | 新增：全部 660 条字符串、6 组 plurals、2 个 string-array 的中文译文（占位符与英文完全一致） |
| `app/src/main/res/values/strings.xml` | 新增 `language_chinese` 键 |
| `app/src/main/java/studio/cluvex/aether/data/LanguagePrefs.kt` | `AppLanguage` 增加 `CHINESE("zh")` |
| `app/src/main/java/studio/cluvex/aether/ui/settings/SettingsScreen.kt` | 语言选择项显示"简体中文" |

使用方式：

- 系统语言为中文时，首次启动即按中文显示；
- 否则进入 **设置 → 语言**，选择**简体中文**，界面、通知、快捷设置磁贴与桌面小部件都会切换，
  无需重启。

技术说明：`values-zh` 只使用语言级限定符 `zh`，因此简体与繁体系统都会回退到它；若你的设备语言为
`zh-HK` / `zh-TW`，界面同样显示简体中文。

## 更新日志

完整历史见 [CHANGELOG.md](CHANGELOG.md)。

<!-- core-sync:zh -->

## 致谢与许可

- [CluvexStudio/Aether](https://github.com/CluvexStudio/Aether) —— 引擎
- [heiher/hev-socks5-tunnel](https://github.com/heiher/hev-socks5-tunnel) —— 隧道核心
- [Arti](https://gitlab.torproject.org/tpo/core/arti) —— 引擎内的 Tor 实现
- [Psiphon](https://github.com/Psiphon-Labs/psiphon-tunnel-core)
- [Xray-core](https://github.com/XTLS/Xray-core) —— 自定义配置

第三方许可：[THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md)。
以 **AGPL-3.0** 发布，见 [LICENSE](LICENSE)。
---
