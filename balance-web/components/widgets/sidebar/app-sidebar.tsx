import { Sidebar, SidebarContent, SidebarGroup, SidebarMenu, SidebarMenuBadge, SidebarMenuButton, SidebarMenuItem, SidebarMenuSub, SidebarMenuSubButton, SidebarMenuSubItem } from "@/components/ui/sidebar";
import AppSidebarHeader from "./app-sidebar-header";
import { MenuItem, SystemName } from "@/lib/types";
import MenuIcon from "./menu-icon";
import Link from "next/link";

type AppSidebarProps = {
    systemName : SystemName,
    menus : MenuItem[]
}

export default function AppSidebar({systemName, menus} : AppSidebarProps) {
    return (
        <Sidebar>
            <AppSidebarHeader systemName={systemName} />

            <SidebarContent>
                <SidebarGroup>
                    <SidebarMenu>
                    {menus.map(menu => 
                        <AppSibeMenuItem menu={menu} key={menu.name} />
                     )}    
                    </SidebarMenu>
                </SidebarGroup>
            </SidebarContent>
        </Sidebar>
    )
}

function AppSibeMenuItem({menu} : {menu : MenuItem}) {

    if(menu.route) {
        return (
            <SidebarMenuItem>
                <SidebarMenuButton render={
                    <Link href={menu.route} />
                }>
                    <MenuIcon icon={menu.icon} />
                    {menu.name}
                </SidebarMenuButton>
            </SidebarMenuItem>
        )
    }

    return (
        <SidebarMenuItem>
            <SidebarMenuButton>
                <MenuIcon icon={menu.icon} />
                {menu.name}
            </SidebarMenuButton>
            <SidebarMenuSub>
                {(menu.subMenus ?? []).map(item => 
                    <SidebarMenuSubItem key={item.name}>
                        <SidebarMenuSubButton render={
                            <Link href={item.route} />
                        }>
                            {item.name}
                        </SidebarMenuSubButton>
                    </SidebarMenuSubItem>
                )}
            </SidebarMenuSub>
        </SidebarMenuItem>
    )
}
