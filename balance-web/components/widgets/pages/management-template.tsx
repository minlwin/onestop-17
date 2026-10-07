import { SubMenuItem } from "@/lib/types"
import PageTemplate from "./page-template"
import React from "react"

type TemplateProps = {
    page : string
    links? : SubMenuItem[]
    children : Readonly<React.ReactNode>
}

export default function ManagementTemplate({page, links, children} : TemplateProps) {
    return (
        <PageTemplate page={page} links={links} system="Management Portal">
            {children}
        </PageTemplate>
    )
}