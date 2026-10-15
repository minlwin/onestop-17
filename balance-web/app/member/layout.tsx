import { SidebarInset, SidebarProvider } from "@/components/ui/sidebar";
import AppSidebar from "@/components/widgets/sidebar/app-sidebar";
import { MenuItem } from "@/lib/types";
import { Metadata } from "next";
import React from "react";

export const metadata: Metadata = {
  title: "Balances | Application",
  description: "Balance application.",
};


const menus:MenuItem[] = [
    {name : "Dashboard", icon: "dashboard", route : "/member"},
    {name : "Balance Report", icon : "balanceReport", route: "/member/balances"},
    {name : "Credit Management", icon: "creditEntry", route : "/member/entry/credit"},
    {name : "Debit Management", icon: "debitEntry", route : "/member/entry/debit"},
    {name : "Organization", icon : "organization", subMenus: [
        {name : "Ledger Management", route : "/member/organization/ledger"},
        {name : "Employee Management", route: "/member/organization/employee"},
        {name : "Company Profile", route: "/member/organization/company"}
    ]},
]

export default function MemberLayout({ children } : { children : Readonly<React.ReactNode>} ) {
    return (
        <SidebarProvider>
            <AppSidebar systemName="Balance Application" menus={menus} />
            <SidebarInset>
                {children}
            </SidebarInset>
        </SidebarProvider>
    )
}