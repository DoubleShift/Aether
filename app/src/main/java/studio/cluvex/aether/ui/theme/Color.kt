package studio.cluvex.aether.ui.theme

import androidx.compose.ui.graphics.Color

// ---------------------------------------------------------------------------
// The Aether light palette.
//
// ONE curated ramp, used by every surface in the app. It used to be a dark navy
// ramp (Navy950..Navy600), which is why every token here is named after the ROLE
// it plays rather than after a colour: the values flipped, the roles did not.
//
// The ramp is built the way the platform's own light themes are: a very light
// neutral page, then surfaces that step towards white as they get closer to the
// content (never transparency, which double-lightens where two cards overlap),
// one constant hue so nothing reads as a different blue, and text tokens chosen
// so the smallest caption still clears 4.5:1 on the lightest surface.
// ---------------------------------------------------------------------------

/** The page behind everything. */
val BgPage = Color(0xFFF4F6FB)
/** Sheets, drawers, the settings surface. */
val BgSheet = Color(0xFFFFFFFF)
/** Elevation 1: a settings group / card. */
val BgCard = Color(0xFFFFFFFF)
/** Elevation 2: a row inside a card, an input field. */
val BgControl = Color(0xFFEEF2F9)
/** Elevation 3: a pressed row, the selected segment's track. */
val BgControlActive = Color(0xFFE3EAF6)
/** Hairlines and dividers. */
val LineSoft = Color(0xFFE3E8F2)
/** Disabled strokes. */
val LineDisabled = Color(0xFFCBD5E4)

// ---- Accents -------------------------------------------------------------

/** Primary action / selected state. */
val AetherBlue = Color(0xFF2563EB)
/** Container tint behind a primary icon. */
val BlueWash = Color(0xFFE4ECFD)
/** The connected accent, shared with the card's travelling edge. */
val AetherMint = Color(0xFF0FA67F)
val MintWash = Color(0xFFDAF5EC)
/** Second light of the animated card edge, cooler than the mint. */
val AetherGlowCyan = Color(0xFF0E9CB8)
val AetherCyan = Color(0xFF12B39B)
val AetherError = Color(0xFFD92D48)
val ErrorWash = Color(0xFFFCE7EB)
/** Warning / "fair" quality. */
val AetherAmber = Color(0xFFC57C00)
/** The Zero Trust / sign-in accent. */
val AetherViolet = Color(0xFF7C5CF0)

// ---- Text ----------------------------------------------------------------

val InkPrimary = Color(0xFF101828)
val InkMuted = Color(0xFF5A6779)
val InkFaint = Color(0xFF8A94A6)

// ---- Connection card ------------------------------------------------------
//
// The card keeps its own two-stop glass gradient because it is the one surface
// in the app that sits on top of the backdrop rather than in the elevation ramp.
// The stops are now derived from the ramp above so the card belongs to the same
// palette as the settings screens.

val CardSurfaceTop = Color(0xFFFFFFFF)
val CardSurfaceBottom = Color(0xFFF3F7FF)

/** Every sub-container inside the card (IP pill, speed strip, protocol strip). */
val CardSubSurface = BgControl

val CardTextPrimary = InkPrimary
val CardTextMuted = InkMuted
val CardTextDim = InkFaint
