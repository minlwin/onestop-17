import { SidebarInset, SidebarProvider } from "@/components/ui/sidebar";
import AppSidebar from "@/components/widgets/sidebar/app-sidebar";
import { MenuItem } from "@/lib/types";
import React from "react";

const menus:MenuItem[] = [
    {name : "Dashboard", icon: "dashboard", route : "/management"},
    {name : "Payment Management", icon: "paymentManagement", route : "/management/payments"},
    {name : "Registrations", icon : "registrationManagement", route: "/management/registrations"},
    {name : "Master Data", icon : "masterData", subMenus: [
        {name : "Payment Plan", route: "/management/master/plans"},
        {name : "Payment Infomation", route : "/management/master/payments"}
    ]},
    {name : "User Management", icon: "userManagement", route: "/management/users"}
]

export default function ManagementLayout({ children } : { children : Readonly<React.ReactNode>} ) {
    return (
        <SidebarProvider>
            <AppSidebar systemName="Management Portal" menus={menus} />
            <SidebarInset>
                {children}
            </SidebarInset>
        </SidebarProvider>
    )
}