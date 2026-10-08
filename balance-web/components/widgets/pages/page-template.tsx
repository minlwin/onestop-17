import { Breadcrumb, BreadcrumbItem, BreadcrumbLink, BreadcrumbList, BreadcrumbPage, BreadcrumbSeparator } from "@/components/ui/breadcrumb"
import { SidebarTrigger } from "@/components/ui/sidebar"
import { SubMenuItem, SystemName } from "@/lib/types"
import { Separator } from "@base-ui/react"
import Link from "next/link"
import React, { Fragment } from "react"

type PageTemplateProps = {
    system : SystemName
    page : string
    links? : SubMenuItem[]
    children : Readonly<React.ReactNode> 
}

export default function PageTemplate({system, page, links, children} : PageTemplateProps) {
    return (
        <>
            <header className="flex h-16 shrink-0 items-center gap-2 transition-[width,height] ease-linear group-has-data-[collapsible=icon]/sidebar-wrapper:h-12">
                <div className="flex items-center gap-2 px-4">
                    <SidebarTrigger className={'-ml-1'} />
                    <Separator orientation="vertical"/>
                    <Breadcrumb>
                        <BreadcrumbList>
                            <BreadcrumbItem>
                                <BreadcrumbPage>{system}</BreadcrumbPage>
                            </BreadcrumbItem>
                            <BreadcrumbSeparator className="hidden md:block" />

                            {links?.map(item => 
                                <Fragment key={item.name}>
                                    <BreadcrumbItem key={item.name}>
                                        <BreadcrumbLink render={
                                            <Link href={item.route} />
                                        }>{item.name}</BreadcrumbLink>
                                    </BreadcrumbItem>
                                    <BreadcrumbSeparator className="hidden md:block" />
                                </Fragment>
                            )}

                            <BreadcrumbItem>
                                <BreadcrumbPage>{page}</BreadcrumbPage>
                            </BreadcrumbItem>
                        </BreadcrumbList>
                    </Breadcrumb>
                </div>
            </header>

            <main className="mx-4 space-y-4">
                {children}
            </main>
        </>
    )
}