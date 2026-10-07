import {
    Users,
    LucideIcon,
    DatabaseCheck,
    ShieldCheck,
    UserPlus,
    Gauge,
    ShoppingCart
} from 'lucide-react'

export const menuIcons = {
    "dashboard" : Gauge,
    "userManagement" : Users,
    "masterData" : DatabaseCheck,
    "planManagement" : ShieldCheck,
    "paymentManagement" : ShoppingCart,
    "registrationManagement" : UserPlus
} satisfies Record<string, LucideIcon>

export type MenuIconType = keyof typeof menuIcons
export type SystemName = "Management Portal" | "Member Portal"


export interface MenuItem {
    name : string
    icon : MenuIconType
    route? : string
    subMenus? : SubMenuItem[]
}

export interface SubMenuItem {
    name : string
    route : string
}