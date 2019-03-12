package levander.merorin.dp.rpg.character

/**
 * @author bin.guo
 * On 2019-03-12
 */
class King : BaseCharacter() {

    override fun fight() {
        print("King is ......")
        this.weaponBehavior.useWeapon()
    }
}