import { menuIcons, MenuIconType } from "@/lib/types";

export default function MenuIcon({ icon, className } : { icon : MenuIconType, className? : string }) {
    const MenuIcon = menuIcons[icon]

    return (
        <MenuIcon className={className} />
    )
}