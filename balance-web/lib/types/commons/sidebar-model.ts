import {
    Users,
    LucideIcon,
    DatabaseCheck,
    ShieldCheck,
    UserPlus,
    Gauge,
    ShoppingCart,
    TrendingUp,
    TrendingDown,
    Cog,
    ChartLine
} from 'lucide-react'

export const menuIcons = {
    "dashboard" : Gauge,
    "userManagement" : Users,
    "masterData" : DatabaseCheck,
    "planManagement" : ShieldCheck,
    "paymentManagement" : ShoppingCart,
    "registrationManagement" : UserPlus,
    "balanceReport" : ChartLine,
    "creditEntry" : TrendingUp,
    "debitEntry" : TrendingDown,
    "organization" : Cog
} satisfies Record<string, LucideIcon>

export type MenuIconType = keyof typeof menuIcons
export type SystemName = "Management Portal" | "Balance Application"


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