import { SubMenuItem } from "@/lib/types"
import PageTemplate from "./page-template"
import React from "react"

type TemplateProps = {
    page : string
    links? : SubMenuItem[]
    children : Readonly<React.ReactNode>
}

export default function MemberTemplate({page, links, children} : TemplateProps) {
    return (
        <PageTemplate page={page} links={links} system="Member Portal">
            {children}
        </PageTemplate>
    )
}