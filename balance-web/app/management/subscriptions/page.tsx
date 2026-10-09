'use client'

import FormsInput from "@/components/forms/forms-input";
import FormsSelect from "@/components/forms/forms-select";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import DetailsLink from "@/components/widgets/details-link";
import NoSearchResult from "@/components/widgets/no-search-result";
import PagerWidget from "@/components/widgets/pager-widget";
import ManagementTemplate from "@/components/widgets/pages/management-template";
import { PageResult, SubscriptionListItem, SubscriptionPageSearch, subscriptionStatusOptions } from "@/lib/types";
import { executeAction } from "@/lib/utils";
import { Search } from "lucide-react";
import { useState } from "react";
import { useForm, UseFormReturn } from "react-hook-form";
import * as action from "@/lib/service/action/management/subscription.action"

export default function PagementManagementPage() {

    const [result, setResult] = useState<PageResult<SubscriptionListItem>>()

    const form = useForm<SubscriptionPageSearch>({defaultValues : {
        status : "",
        appliedFrom: "",
        appliedTo: "",
        keyword : "",
        page : 0,
        size : Number(process.env.NEXT_PUBLIC_PAGE_SIZE)
    }})

    function search(searchForm : SubscriptionPageSearch) {
        form.setValue("page", 0)
        executeAction(async () => {
            const response = await action.search(searchForm)
            setResult(response)
        })
    }

    function onPageChange(page : number) {
        form.setValue("page", page)
        executeAction(async () => {
            const response = await action.search(form.getValues())
            setResult(response)
        })
    }


    return (
        <ManagementTemplate page="Subscriptions" >
            <SearchForm form={form} onSearch={search} />
            <SearchResultOptional result={result} onPageChange={onPageChange} />
        </ManagementTemplate>
    )
}

function SearchForm({onSearch, form} : {
    onSearch : (form : SubscriptionPageSearch) => void,
    form : UseFormReturn<SubscriptionPageSearch, any, SubscriptionPageSearch>
}) {

    return (
        <Card>
            <CardContent>
                <form onSubmit={form.handleSubmit(onSearch)} className="flex gap-4 items-end">
                    <FormsSelect control={form.control} name="status" label="Status" options={[
                        {label : "Search All", value : ""},
                        ... subscriptionStatusOptions.map(item => ({label : item, value : item}))
                    ]} className="w-fit" />

                    <FormsInput control={form.control} name="appliedFrom" label="Register From" type="date" className="w-fit"/>
                    <FormsInput control={form.control} name="appliedTo" label="Register To" type="date" className="w-fit"/>
                    <FormsInput control={form.control} name="keyword" label="Keyword" className="w-fit"/>

                    <Button type="submit">
                        <Search /> Search
                    </Button>
                </form>
            </CardContent>
        </Card>
    )
}

function SearchResultOptional({result, onPageChange} : {
    result? : PageResult<SubscriptionListItem>,
    onPageChange : (page : number) => void
}) {
    if(!result || result.totalCount === 0) {
        return (
            <NoSearchResult data="subscription" />
        )
    }

    return (
        <SearchResult result={result} onPageChange={onPageChange} />
    )
}

function SearchResult({result, onPageChange} : {
    result : PageResult<SubscriptionListItem>, 
    onPageChange : (page : number) => void
}) {
    const {contents, ... pageInfo} = result

    return (
        <div className="space-y-4">
            <Card>
                <CardContent className="space-y-4">
                    <Table>
                        <TableHeader>
                            <TableRow>
                                <TableHead>Partner</TableHead>
                                <TableHead>Company</TableHead>
                                <TableHead>Phone</TableHead>
                                <TableHead>Email</TableHead>
                                <TableHead>Plan</TableHead>
                                <TableHead className="text-end">Months</TableHead>
                                <TableHead className="text-end">Paid</TableHead>
                                <TableHead>Status</TableHead>
                                <TableHead>Registered At</TableHead>
                                <TableHead>Approved At</TableHead>
                                <TableHead></TableHead>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                            {contents.map(item => 
                                <TableRow key={item.id}>
                                    <TableCell>{item.partnerName}</TableCell>
                                    <TableCell>{item.company}</TableCell>
                                    <TableCell>{item.phone}</TableCell>
                                    <TableCell>{item.email}</TableCell>
                                    <TableCell>{item.planName}</TableCell>
                                    <TableCell className="text-end">{item.months} Months</TableCell>
                                    <TableCell className="text-end">{item.amount.toLocaleString()}</TableCell>
                                    <TableCell>{item.status}</TableCell>
                                    <TableCell>{item.appliedAt}</TableCell>
                                    <TableCell>{item.approvedAt}</TableCell>
                                    <TableCell>
                                        <DetailsLink route={`/management/subscriptions/${item.id}`} />
                                    </TableCell>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                </CardContent>
            </Card>
            <PagerWidget pageInfo={pageInfo} onPageChange={onPageChange} />
        </div>
    )
}