package levander.merorin.dp.rpg.character

/**
 * @author bin.guo
 * On 2019-03-12
 */
class Queen : BaseCharacter() {

    override fun fight() {
        print("Queen is ......")
        this.weaponBehavior.useWeapon()
    }
}